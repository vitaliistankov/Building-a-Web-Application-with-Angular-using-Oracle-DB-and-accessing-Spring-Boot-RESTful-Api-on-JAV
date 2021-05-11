package com.rest.main;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.main.DTOs.ResourceDTO;
import com.rest.services.main.implementations.ResourceService;

@RestController
@RequestMapping(value = "/resource")
public class ResourceController {
	
	@Autowired
	private ResourceService service;
	
	@RequestMapping(method = RequestMethod.POST,value = "/create")
	public void create(@RequestBody ResourceDTO dto) {
		
		this.service.create(dto);
		
	}
	
	@RequestMapping(method = RequestMethod.PUT,value = "/update/{id}")
	public void update(@RequestBody ResourceDTO dto,@PathVariable int id) {
		
		this.service.update(dto, id);
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/getById/{id}")
	public ResourceDTO getById(@PathVariable int id) {
		
		return this.service.getById(id);
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/getAll")
	public List<ResourceDTO> getAll() {
		
		return this.service.getAll();
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value = "/delete/{id}")
	public void delete(@PathVariable int id) {
		
		this.service.deleteById(id);
		
	}

}
