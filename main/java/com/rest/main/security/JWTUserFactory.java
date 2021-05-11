package com.rest.main.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.rest.main.models.Role;
import com.rest.main.models.User;

@Component
public final class JWTUserFactory {
	
	public JWTUserFactory() {
		
	}
	
	public static JWTUser create(User user) {
		
		return new JWTUser(user.getUser_id(), user.getFirst_name(), user.getLast_name(), user.getUser_unique_id(),
				user.getPassword(), user.getMail(), mapToGrantedAuthorities(user.getRole()));
		
	}
	
	public static List<GrantedAuthority> mapToGrantedAuthorities(Role role){
		
		GrantedAuthority authority =  new SimpleGrantedAuthority(role.getRole_name());
		
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		
		authList.add(authority);
		
		return authList;
		
	}

}
