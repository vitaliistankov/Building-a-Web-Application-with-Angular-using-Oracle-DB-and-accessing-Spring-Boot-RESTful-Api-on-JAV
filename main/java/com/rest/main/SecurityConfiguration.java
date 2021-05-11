package com.rest.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.rest.main.security.JWTConfigurer;
import com.rest.main.security.JwtTokenProvider;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtTokenProvider provider;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean () throws Exception {
		return super.authenticationManagerBean();
		
	}
	
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http
		.httpBasic()
		.disable()
		.csrf()
		.disable()
		.cors().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests()
		.antMatchers("/user/login").permitAll()
		.antMatchers("/user/**").hasRole("admin")
		.anyRequest().authenticated()
		.and()
		.apply(new JWTConfigurer(this.provider));
		     
		
	}

}
