package sbnz.skincare.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sbnz.skincare.dto.JwtAuthenticationRequest;
import sbnz.skincare.dto.UserTokenState;
import sbnz.skincare.exception.BadRequestException;
import sbnz.skincare.facts.User;
import sbnz.skincare.security.util.TokenUtils;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	protected final Log LOGGER = LogFactory.getLog(getClass());

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(
			@RequestBody @Valid JwtAuthenticationRequest authenticationRequest) {

		Authentication authentication;
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (AuthenticationException | BadRequestException e) {

			return new ResponseEntity<>("Incorrect username or password!", HttpStatus.FORBIDDEN);

		}

		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Create token
		User user = (User) authentication.getPrincipal();
		String jwt = tokenUtils.generateToken(user.getUsername(), "DERMATOLOGIST"); // TODO
		int expiresIn = tokenUtils.getExpiredIn();

		return ResponseEntity.ok().body(new UserTokenState(jwt, expiresIn));
	}
}
