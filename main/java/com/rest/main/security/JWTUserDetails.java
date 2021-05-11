package com.rest.main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.main.DTOs.UserDTO;
import com.rest.main.models.User;
import com.rest.services.main.implementations.UserService;

@Service(value = "jwt")
@Transactional(readOnly = true)
public class JWTUserDetails implements UserDetailsService{
	
	@Autowired
	private UserService service;
	

	@Override
	@Transactional(readOnly = false)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDTO dto = service.getByUsername(username);
		
		User user = new User();
		
		user.setDate_created(dto.getDate_created());
		user.setDate_modified(dto.getDate_modified());
		user.setFirst_name(dto.getFirst_name());
		user.setLast_name(dto.getLast_name());
		user.setMail(dto.getMail());
		user.setPassword(dto.getPassword());
		user.setRole(dto.getRole());
		user.setRole_id(dto.getRole_id());
		user.setSalt(dto.getSalt());
		user.setUser_id(dto.getUser_id());
		user.setUser_unique_id(dto.getUsername());
		
		JWTUser jwtUser = JWTUserFactory.create(user);
		
		return jwtUser;
	}

}
