package com.rest.service.main.interfaces;

import java.util.List;

import com.rest.main.DTOs.RoleDTO;


public interface RoleServiceInterface {
	
	void create(RoleDTO roleDTO);

	void update(RoleDTO roleDTO,int id);

	RoleDTO getById(int resId);

	List<RoleDTO> getAll();

	boolean deleteById(int resId);
}
