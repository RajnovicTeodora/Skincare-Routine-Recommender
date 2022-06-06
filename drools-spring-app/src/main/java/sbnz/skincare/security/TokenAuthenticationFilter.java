package sbnz.skincare.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import sbnz.skincare.security.util.TokenUtils;

public class TokenAuthenticationFilter extends OncePerRequestFilter  {//OncePerRequestFilter UsernamePasswordAuthenticationFilter

	private TokenUtils tokenUtils;

	private UserDetailsService userDetailsService;

	protected final Log LOGGER = LogFactory.getLog(getClass());

	public TokenAuthenticationFilter(TokenUtils tokenUtils, UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
		this.tokenUtils = tokenUtils;
	}

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {


		String username;

		// 1. Preuzimanje JWT tokena i cookie iz zahteva
		String authToken = tokenUtils.getToken(request);
		if(authToken != null && authToken.startsWith("\"")){
			authToken = authToken.substring(1, authToken.length()-1);
		}
		try {

			if (authToken != null) {

				// 2. Citanje korisnickog imena iz tokena
				username = tokenUtils.getUsernameFromToken(authToken);

				if (username != null) {

					// 3. Preuzimanje korisnika na osnovu username-a
					UserDetails userDetails = userDetailsService.loadUserByUsername(username);

					// 4. Provera da li je prosledjeni token validan
					if (tokenUtils.validateToken(authToken, userDetails)) {

						// 5. Kreiraj autentifikaciju
						TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
						authentication.setToken(authToken);
						SecurityContextHolder.getContext().setAuthentication(authentication);
					}
				}
			}

		} catch (ExpiredJwtException ex) {
			LOGGER.debug("Token expired!");
		}

		// prosledi request dalje u sledeci filter
		chain.doFilter(request, response);
	}

}
