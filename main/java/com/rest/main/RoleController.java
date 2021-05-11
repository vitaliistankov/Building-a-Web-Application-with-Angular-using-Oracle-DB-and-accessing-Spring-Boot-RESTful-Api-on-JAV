package com.rest.main;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.main.DTOs.RoleDTO;
import com.rest.main.models.Role;
import com.rest.main.models.User;
import com.rest.repository.main.interfaces.RoleRepository;
import com.rest.repository.main.interfaces.UserRepository;
import com.rest.services.main.implementations.RoleService;


@Component
@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired(required = true)
	private RoleService roleService;

	@Autowired(required = true)
	private UserRepository userRepo;

	@Autowired(required = true)
	private RoleRepository roleRepo;

	@RequestMapping(method = RequestMethod.POST, value = "/create")
	public void create(@RequestBody RoleDTO roleDTO) {

		roleService.create(roleDTO);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/check")
	public void check() {
		Optional<Role> role = roleRepo.findById(2);

		Optional<User> user = userRepo.findById(2);

		// String name = role.get().getUsers().get(0).getFirst_name();

		int s = 5;

		System.out.println(" " + role + user + s);

	}
	
	@RequestMapping(value="/update/{id}",consumes = "application/json")
	public void update(@RequestBody RoleDTO roleDTO,@PathVariable int id) {
		
		roleService.update(roleDTO,id);
		
	}
	
	@RequestMapping(value="/getAll",produces  = "application/json")
	public List<RoleDTO> getAll() {
		
		return roleService.getAll();
		
	}
	
	@RequestMapping(value="/getById/{id}",consumes = "application/json")
	public RoleDTO getById(@PathVariable int id) {
		
		return roleService.getById(id);
		
	}
	
	@RequestMapping(value="/delete/{id}")
	public void delete(@PathVariable int id) {
		
		roleService.deleteById(id);
		
	}

}

