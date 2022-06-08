package sbnz.skincare.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import sbnz.skincare.facts.User;
import sbnz.skincare.service.UserService;


@Component
public class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {


    private final UserService userService;

    @Autowired
    public TokenAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {

        User user = this.userService.findByUsername(username);

        BCryptPasswordEncoder enc = new BCryptPasswordEncoder();


        if (enc.matches((String) authentication.getCredentials(), user.getPassword()))
            return user;
        else
            throw new BadCredentialsException("Incorrect password");

    }

}