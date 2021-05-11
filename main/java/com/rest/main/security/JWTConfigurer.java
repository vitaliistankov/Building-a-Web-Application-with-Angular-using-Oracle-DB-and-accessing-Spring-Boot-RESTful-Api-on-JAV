package com.rest.main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


public class JWTConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>{
	
	private JwtTokenProvider provider;
	
	@Autowired
	public JWTConfigurer(JwtTokenProvider provider) {
		this.provider = provider;
	}
	
	@Override
	public void configure(HttpSecurity http) {
		
		JWTTokenFilter filter = new JWTTokenFilter(provider);
		
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		
	}
	

}
