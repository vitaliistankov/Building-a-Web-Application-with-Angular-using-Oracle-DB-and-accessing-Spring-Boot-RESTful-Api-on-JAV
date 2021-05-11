package com.rest.main.security;

import java.util.Date;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.rest.main.models.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
	
	@Qualifier(value = "jwt")
	@Autowired
	private UserDetailsService service;

	@Value("${jwt.token.secret}")
	private String secret;

	@Value("${jwt.token.expired}")
	private long validationInMiliseconds;

	@Bean
	public BCryptPasswordEncoder passEncoder() {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		return passwordEncoder;

	}

	@PostConstruct
	protected void init() {
		
		this.secret = Base64.getEncoder().encodeToString(secret.getBytes());
		
	}

	public String create(String username, List<GrantedAuthority> roles) {
		
		Claims claims = Jwts.claims().setSubject(username);
		
		claims.put("roles",getRoleNames(roles));
		
		Date nowDate = new Date();
		
		Date validity = new Date(nowDate.getTime() + validationInMiliseconds );
		
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(nowDate)
				.setExpiration(validity)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
		
		
	}

	public Authentication getAuthentication(String token) {

		UserDetails details = service.loadUserByUsername(getUsername(token));
		
		return new UsernamePasswordAuthenticationToken(details, details.getPassword(), details.getAuthorities());
		
	}

	public String getUsername(String token) {
		
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateToken(String token) {
		
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			
			if (claims.getBody().getExpiration().before(new Date())) {
				return false;
			}
			
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
			throw new JwtAuthenticationException("JWT is expired or invalid!");
			
		}

	}
	
	public String resolveToken(HttpServletRequest req) {
		
		String bearerTokenString = req.getHeader("Authorization");
		
		if (bearerTokenString != null && bearerTokenString.startsWith("Bearer ")) {
			
			return bearerTokenString.substring(6,bearerTokenString.length());
			
		}
		return null;
		
	}

	public List<String> getRoleNames(List<GrantedAuthority> roles) {

		List<String> roleNames = new ArrayList<String>();

		roles.forEach(x -> roleNames.add(((Role) x).getRole_name()));

		return roleNames;
	}

}
