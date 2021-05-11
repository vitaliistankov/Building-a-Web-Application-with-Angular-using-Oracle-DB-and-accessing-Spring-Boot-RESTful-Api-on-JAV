package com.rest.service.main.interfaces;

import java.util.List;

import com.rest.main.DTOs.ResourceDTO;

public interface ResourceServiceInterface {

	void create(ResourceDTO resDTO);

	void update(ResourceDTO resDTO,int id);

	ResourceDTO getById(int resId);

	List<ResourceDTO> getAll();

	boolean deleteById(int resId);
	
}
