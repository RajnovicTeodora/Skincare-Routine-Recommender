package sbnz.skincare.security.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

// Utility class for JSON Web Tokens
@Component
public class TokenUtils {

    // Token publisher
    @Value("skincare-recommender")
    private String APP_NAME;

    // Secret JWT https://jwt.io/
    @Value("somesecret")
    public String SECRET;

    // Token expiration time - 15 minutes
    @Value("900000")
    private int EXPIRES_IN;

    // Header name for JWT
    @Value("Authorization")
    private String AUTH_HEADER;

    private static final String AUDIENCE_WEB = "web";

    // Algorithm for signing JWT
    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    // ============= Functions for generating JWT token =============

    /**
     * Functions for generating JWT token.
     *
     * @param username Username of user which the token is being generated for
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
                .claim("username", username)
                .signWith(SIGNATURE_ALGORITHM, SECRET)
                .compact();
    }

    /**
     * Function for defining the type of device that the JWT token is being created for.
     *
     * @return Type of device.
     */
    private String generateAudience() {

        return AUDIENCE_WEB;
    }

    /**
     * Function for generating the date untill the JWT token is valid to.
     *
     * @return Date to which the token is valid to.
     */
    private Date generateExpirationDate() {
        return new Date(new Date().getTime() + EXPIRES_IN);
    }

    // =================================================================

    // ============= Functions for reading information from JWT token =============

    /**
     * Function for getting JWT token from request.
     *
     * @param request HTTP request from client.
     * @return JWT token or null if the token isn't placed in the
     * right HTTP request header.
     */
    public String getToken(HttpServletRequest request) { // TODO
        String authHeader = getAuthHeaderFromHeader(request);

        // JWT is passed through the 'Authorization' header in format:
        // Bearer
        // eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7); // Ignore the Bearer prefix
        }

        return null;
    }

    /**
     * Function for getting the owner of the token (username).
     *
     * @param token JWT token.
     * @return Username from token or null if non existent.
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
     * Function for getting the date of when the token was created.
     *
     * @param token JWT token.
     * @return Date when it was created.
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
     * Function for getting the type of device.
     *
     * @param token JWT token.
     * @return Type of device.
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
     * Function for getting the date to which the token is valid to.
     *
     * @param token JWT token.
     * @return Date to which it lasts.
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

    /**
     * Function for getting the algorithm for signing.
     *
     * @param token JWT token.
     * @return Signing algorithm.
     */
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
     * Function for reading all the JWT token data.
     *
     * @param token JWT token.
     * @return Data from the token.
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

        // Get the data with - claims.get(key)
        return claims;
    }

    // =================================================================

    // ============= Functions for JWT token validation =============

    /**
     * Function for JWT token validation.
     *
     * @param token       JWT token.
     * @param userDetails Information about the owner of the JWT token.
     * @return Information if the token is valid or not.
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
     * Function for getting the token expiration period.
     *
     * @return Token expiration period.
     */
    public int getExpiredIn() {
        return EXPIRES_IN;
    }

    /**
     * Function for getting the AUTH_HEADER content from the request.
     *
     * @param request HTTP request.
     * @return Content from AUTH_HEADER.
     */
    public String getAuthHeaderFromHeader(HttpServletRequest request) {
        return request.getHeader(AUTH_HEADER);
    }

}
