package sbnz.skincare.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import sbnz.skincare.security.RestAuthenticationEntryPoint;
import sbnz.skincare.security.util.TokenUtils;
import sbnz.skincare.service.CustomUserDetailsService;



@Configuration
//@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private CustomUserDetailsService customUserDetailsService;


	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Autowired
	public WebSecurityConfig(RestAuthenticationEntryPoint unauthorizedHandler,
			CustomUserDetailsService userDetailsService) {
		this.restAuthenticationEntryPoint = unauthorizedHandler;
		this.customUserDetailsService = userDetailsService;
	}

	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(customUserDetailsService)
				.passwordEncoder(new BCryptPasswordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
    
	@Autowired
	private TokenUtils tokenUtils;

	// Definisemo prava pristupa za zahteve ka odredjenim URL-ovima/rutama
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();

		http
                .csrf()
                .disable()
                //.exceptionHandling()
                //.authenticationEntryPoint(this.restAuthenticationEntryPoint)
                //.and()
                //.sessionManagement()
                //.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //.and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST).permitAll()
                .antMatchers(HttpMethod.GET).permitAll()
                .antMatchers("/routine/getRoutineRecommendation").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/auth/switchToActiveAccount").permitAll()
                // za svaki drugi zahtev korisnik mora biti autentifikovan
                .anyRequest().authenticated().and()

                // za development svrhe ukljuci konfiguraciju za CORS iz WebConfig klase
                .cors();

		/*
		http
				
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
				.authorizeRequests().antMatchers("/auth/**").permitAll()
				.antMatchers("/h2-console/**").permitAll() 
				.antMatchers("/api").permitAll() 
				.anyRequest().authenticated().and()
				.cors().and()
				.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, customUserDetailsService),
						BasicAuthenticationFilter.class);
		http.csrf().disable();
		http.headers().xssProtection().and().contentSecurityPolicy("script-src 'self'");
		*/
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
	
		web.ignoring().antMatchers(HttpMethod.POST, "/auth/login", "/routine/getRoutineRecommendation");
		web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "favicon.ico", "/**/*.html",
				"/**/*.css", "/**/*.js");
	}

}
