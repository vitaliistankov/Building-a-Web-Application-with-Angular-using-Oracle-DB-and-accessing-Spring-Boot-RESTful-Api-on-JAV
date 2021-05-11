package com.rest.main.models;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserPrincipal implements UserDetails{
	
	private final User user;
	private final int user_id;
	private final String first_name;
	private final String last_name;
	private final String username;
	private final String password;
	private final String mail;
	private final Collection<GrantedAuthority> authorities;
	
	public UserPrincipal(User user) {
		
		this.user = user;
		this.user_id = user.getUser_id();
		this.first_name = user.getFirst_name();
		this.last_name = user.getLast_name();
		this.username = user.getUser_unique_id();
		this.password = user.getPassword();
		this.mail = user.getMail();
		this.authorities = new ArrayList<GrantedAuthority>();
		this.init(user);
		
	}
	
	private void init(User user) {
		
		GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getRole_name());

		this.authorities.add(authority);
	}
	
private static final long serialVersionUID = 1L;
	


	@Override
	public Collection<GrantedAuthority> getAuthorities() {
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

	public String getUser_unique_id() {
		return username;
	}

	public String getMail() {
		return this.mail;
	}

	

}
