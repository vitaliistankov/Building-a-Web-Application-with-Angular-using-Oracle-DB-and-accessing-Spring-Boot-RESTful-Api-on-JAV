package com.rest.service.main.interfaces;

import java.util.List;

import com.rest.main.DTOs.UserDTO;


public interface UserServiceInterface {
	
	void create(UserDTO userDTO);

	void update(UserDTO userDTO,int id);

	UserDTO getById(int userId);
	
	UserDTO getByUsername(String userName);

	List<UserDTO> getAll();

	boolean deleteById(int userId);
}
