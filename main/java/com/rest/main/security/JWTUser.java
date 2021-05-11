package com.rest.main.security;

import java.util.Collection;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JWTUser implements UserDetails{

	
	private static final long serialVersionUID = 1L;
	
	private final int user_id;
	private final String first_name;
	private final String last_name;
	private final String username;
	private final String password;
	private final String mail;
	private final Collection<? extends GrantedAuthority> authorities;
	
	
	public JWTUser(int user_id,String first_name,String last_name,String username,String password,String mail,Collection<? extends GrantedAuthority> authorities) {
		this.user_id = user_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
		this.password = password;
		this.mail = mail;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}
	
	@JsonIgnore
	public int getUser_id() {
		return this.user_id;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getFirst_name() {
		return first_name;
	}

	public String getLast_name() {
		return last_name;
	}


	public String getMail() {
		return this.mail;
	}

}
