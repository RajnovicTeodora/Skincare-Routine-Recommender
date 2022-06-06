package sbnz.skincare.security.util;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

// Utility klasa za rad sa JSON Web Tokenima
@Component
public class TokenUtils {

	// Izdavac tokena
	@Value("spring-security-example")
	private String APP_NAME;

	// Tajna koju samo backend aplikacija treba da zna kako bi mogla da generise i
	// proveri JWT https://jwt.io/
	@Value("somesecret")
	public String SECRET;

	// Period vazenja tokena - 15 minuta
	@Value("900000")
	private int EXPIRES_IN;

	// Naziv headera kroz koji ce se prosledjivati JWT u komunikaciji server-klijent
	@Value("Authorization")
	private String AUTH_HEADER;

	// Naziv headera kroz koji ce se prosledjivati cookie u komunikaciji
	// server-klijent
	@Value("Cookie")
	private String COOKIE_HEADER;

	@Value("Fingerprint")
	private String FINGERPRINT_HEADER;

	// Moguce je generisati JWT za razlicite klijente (npr. web i mobilni klijenti
	// nece imati isto trajanje JWT,
	// JWT za mobilne klijente ce trajati duze jer se mozda aplikacija redje koristi
	// na taj nacin)
	// Radi jednostavnosti primera, necemo voditi racuna o uređaju sa kojeg zahtev
	// stiže.
	// private static final String AUDIENCE_UNKNOWN = "unknown";
	// private static final String AUDIENCE_MOBILE = "mobile";
	// private static final String AUDIENCE_TABLET = "tablet";

	private static final String AUDIENCE_WEB = "web";

	// Algoritam za potpisivanje JWT
	private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

	private final SecureRandom secureRandom = new SecureRandom();

	// ============= Funkcije za generisanje JWT tokena =============

	/**
	 * Funkcija za generisanje JWT tokena.
	 *
	 * @param username Korisničko ime korisnika kojem se token izdaje
	 * @return JWT token
	 */
	public String generateToken(String username, String role) {
		
		return Jwts.builder()
				.setIssuer(APP_NAME)
				.setSubject(username)
				.setAudience(generateAudience())
				.setIssuedAt(new Date())
				.setExpiration(generateExpirationDate())
				.claim("role", role)
				.signWith(SIGNATURE_ALGORITHM, SECRET)
				.compact();
	}

	public String generateFingerprint() {
		// Generisanje random string-a koji ce predstavljati fingerprint za korisnika
		byte[] randomFgp = new byte[50];
		this.secureRandom.nextBytes(randomFgp);
		return DatatypeConverter.printHexBinary(randomFgp);
	}

	/**
	 * Funkcija za utvrđivanje tipa uređaja za koji se JWT kreira.
	 *
	 * @return Tip uređaja.
	 */
	private String generateAudience() {

		// Moze se iskoristiti org.springframework.mobile.device.Device objekat za
		// odredjivanje tipa uredjaja sa kojeg je zahtev stigao.
		// https://spring.io/projects/spring-mobile

		// String audience = AUDIENCE_UNKNOWN;
		// if (device.isNormal()) {
		// audience = AUDIENCE_WEB;
		// } else if (device.isTablet()) {
		// audience = AUDIENCE_TABLET;
		// } else if (device.isMobile()) {
		// audience = AUDIENCE_MOBILE;
		// }

		return AUDIENCE_WEB;
	}

	/**
	 * Funkcija generiše datum do kog je JWT token validan.
	 *
	 * @return Datum do kojeg je JWT validan.
	 */
	private Date generateExpirationDate() {
		return new Date(new Date().getTime() + EXPIRES_IN);
	}

	// =================================================================

	// ============= Funkcije za citanje informacija iz JWT tokena =============

	/**
	 * Funkcija za preuzimanje JWT tokena iz zahteva.
	 *
	 * @param request HTTP zahtev koji klijent šalje.
	 * @return JWT token ili null ukoliko se token ne nalazi u odgovarajućem
	 *         zaglavlju HTTP zahteva.
	 */
	public String getToken(HttpServletRequest request) {
		String authHeader = getAuthHeaderFromHeader(request);

		// JWT se prosledjuje kroz header 'Authorization' u formatu:
		// Bearer
		// eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7); // preuzimamo samo token (vrednost tokena je nakon "Bearer " prefiksa)
		}

		return null;
	}

//Fingerprint%3DA9DB4924B18ACAA4DD4D3B3B270C6704C262BC0F3039CE036F4C5BC3EEF708B0D99A66D2D64176AB0018744ECEB1B65CCB1C%3B%20HttpOnly%3B%20Secure%3B%20Path%3D%2F
	public String getFingerprintFromCookie(HttpServletRequest request) {
		String userFingerprint = null;

		if (request.getCookies() != null && request.getCookies().length > 0) {

			List<Cookie> cookies = Arrays.stream(request.getCookies()).collect(Collectors.toList());
			Optional<Cookie> cookie = cookies.stream().filter(c -> "Fingerprint".equals(c.getName())).findFirst();

			if (cookie.isPresent()) {
				userFingerprint = cookie.get().getValue();
			}
		}
		return userFingerprint;
	}

	/**
	 * Funkcija za preuzimanje vlasnika tokena (korisničko ime).
	 *
	 * @param token JWT token.
	 * @return Korisničko ime iz tokena ili null ukoliko ne postoji.
	 */
	public String getUsernameFromToken(String token) {
		String username;

		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			username = claims.getSubject();
		} catch (ExpiredJwtException ex) {
			throw ex;
		} catch (Exception e) {
			username = null;
		}

		return username;
	}

	/**
	 * Funkcija za preuzimanje datuma kreiranja tokena.
	 *
	 * @param token JWT token.
	 * @return Datum kada je token kreiran.
	 */
	public Date getIssuedAtDateFromToken(String token) {
		Date issueAt;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			issueAt = claims.getIssuedAt();
		} catch (ExpiredJwtException ex) {
			throw ex;
		} catch (Exception e) {
			issueAt = null;
		}
		return issueAt;
	}

	/**
	 * Funkcija za preuzimanje informacije o uređaju iz tokena.
	 *
	 * @param token JWT token.
	 * @return Tip uredjaja.
	 */
	public String getAudienceFromToken(String token) {
		String audience;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			audience = claims.getAudience();
		} catch (ExpiredJwtException ex) {
			throw ex;
		} catch (Exception e) {
			audience = null;
		}
		return audience;
	}

	/**
	 * Funkcija za preuzimanje datuma do kada token važi.
	 *
	 * @param token JWT token.
	 * @return Datum do kojeg token važi.
	 */
	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (ExpiredJwtException ex) {
			throw ex;
		} catch (Exception e) {
			expiration = null;
		}

		return expiration;
	}

	private String getAlgorithmFromToken(String token) {
		String algorithm;
		try {
			algorithm = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getHeader().getAlgorithm();
		} catch (ExpiredJwtException ex) {
			throw ex;
		} catch (Exception e) {
			algorithm = null;
		}
		return algorithm;
	}

	/**
	 * Funkcija za čitanje svih podataka iz JWT tokena
	 *
	 * @param token JWT token.
	 * @return Podaci iz tokena.
	 */
	private Claims getAllClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
		} catch (ExpiredJwtException ex) {
			throw ex;
		} catch (Exception e) {
			claims = null;
		}

		// Preuzimanje proizvoljnih podataka je moguce pozivom funkcije claims.get(key)

		return claims;
	}

	// =================================================================

	// ============= Funkcije za validaciju JWT tokena =============

	/**
	 * Funkcija za validaciju JWT tokena.
	 *
	 * @param token       JWT token.
	 * @param userDetails Informacije o korisniku koji je vlasnik JWT tokena.
	 * @return Informacija da li je token validan ili ne.
	 */
	public Boolean validateToken(String token, UserDetails userDetails) {

		final String username = getUsernameFromToken(token);
		boolean isUsernameValid = username != null 
				&& username.equals(userDetails.getUsername());
		boolean isAlgorithmValid = SIGNATURE_ALGORITHM.getValue().equals(getAlgorithmFromToken(token));

		return isUsernameValid && isAlgorithmValid;
	}

	// =================================================================

	/**
	 * Funkcija za preuzimanje perioda važenja tokena.
	 *
	 * @return Period važenja tokena.
	 */
	public int getExpiredIn() {
		return EXPIRES_IN;
	}

	/**
	 * Funkcija za preuzimanje sadržaja AUTH_HEADER-a iz zahteva.
	 *
	 * @param request HTTP zahtev.
	 * @return Sadrzaj iz AUTH_HEADER-a.
	 */
	public String getAuthHeaderFromHeader(HttpServletRequest request) {
		return request.getHeader(AUTH_HEADER);
	}

	public String getFingerprintHeaderFromHeader(HttpServletRequest request) {
		return request.getHeader(FINGERPRINT_HEADER);
	}

}
