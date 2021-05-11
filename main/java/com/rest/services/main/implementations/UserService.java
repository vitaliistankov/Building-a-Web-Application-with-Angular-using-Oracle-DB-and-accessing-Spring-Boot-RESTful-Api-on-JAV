package com.rest.services.main.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.main.DTOs.UserDTO;
import com.rest.main.models.Role;
import com.rest.main.models.User;
import com.rest.repository.main.interfaces.RoleRepository;
import com.rest.repository.main.interfaces.UserRepository;
import com.rest.service.main.interfaces.UserServiceInterface;

@Service
@Transactional(readOnly = true)
public class UserService implements UserServiceInterface {

	@Autowired
	private  UserRepository userRepo;
	@Autowired
	private  RoleRepository roleRepo;
	@Autowired
	private  BCryptPasswordEncoder encoder;
	
	

	@Override
	@Transactional(readOnly = false)
	public void create(UserDTO userDTO) {

		try {

			User user = new User();

			user.setDate_created(userDTO.getDate_created());
			user.setDate_modified(userDTO.getDate_created());
			user.setMail(userDTO.getMail());
			user.setFirst_name(userDTO.getFirst_name());
			user.setLast_name(userDTO.getLast_name());
			user.setPassword(encoder.encode(userDTO.getPassword()));
			user.setRole_id(userDTO.getRole_id());
			user.setUser_unique_id(userDTO.getUsername());
			user.setSalt(userDTO.getSalt());

			Optional<Role> role = roleRepo.findById(userDTO.getRole_id());

			user.setRole(role.get());

			userRepo.save(user);
		} catch (Exception e) {

			System.out.println(e.getMessage());
			e.getStackTrace();
		}

	}

	@Override
	@Transactional(readOnly = false)
	public void update(UserDTO userDTO, int id) {

		try {

			Optional<User> user = userRepo.findById(id);

			user.get().setDate_created(userDTO.getDate_created());
			user.get().setDate_modified(userDTO.getDate_modified());
			user.get().setFirst_name(userDTO.getFirst_name());
			user.get().setLast_name(userDTO.getLast_name());
			user.get().setMail(userDTO.getMail());
			user.get().setPassword(userDTO.getPassword());
			user.get().setRole_id(userDTO.getRole_id());
			user.get().setSalt(userDTO.getSalt());
			user.get().setUser_unique_id(userDTO.getUsername());

			Optional<Role> role = roleRepo.findById(userDTO.getRole_id());

			user.get().setRole(role.get());

			userRepo.save(user.get());

		} catch (Exception e) {

			System.out.println(e.getMessage());
			e.getStackTrace();
		}

	}

	@Override
	@Transactional(readOnly = false)
	public UserDTO getById(int userId) {
		UserDTO dto = new UserDTO();
		try {
			Optional<User> user = userRepo.findById(userId);

			dto.setUser_id(user.get().getUser_id());
			dto.setUsername(user.get().getUser_unique_id());
			dto.setSalt(user.get().getSalt());
			dto.setRole_id(user.get().getRole_id());
			dto.setPassword(user.get().getPassword());
			dto.setMail(user.get().getMail());
			dto.setLast_name(user.get().getLast_name());
			dto.setFirst_name(user.get().getFirst_name());
			dto.setDate_modified(user.get().getDate_modified());
			dto.setDate_created(user.get().getDate_created());
			
			Optional<Role> role = roleRepo.findById(user.get().getRole_id());

			dto.setRole(role.get());
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	@Transactional(readOnly = false)
	public List<UserDTO> getAll() {

		List<UserDTO> dtoList = new ArrayList<>();

		try {
			List<User> userList = (List<User>) userRepo.findAll();

			for (User user : userList) {

				Optional<Role> role = roleRepo.findById(user.getRole_id());

				UserDTO dto = new UserDTO();
				dto.setUser_id(user.getUser_id());
				dto.setUsername(user.getUser_unique_id());
				dto.setSalt(user.getSalt());
				dto.setRole_id(user.getRole_id());
				dto.setPassword(user.getPassword());
				dto.setMail(user.getMail());
				dto.setLast_name(user.getLast_name());
				dto.setFirst_name(user.getFirst_name());
				dto.setDate_modified(user.getDate_modified());
				dto.setDate_created(user.getDate_created());
				dto.setRole(role.get());
				dtoList.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteById(int userId) {
		try {
			userRepo.deleteById(userId);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return true;
	}


	@Override
	@Transactional(readOnly = false)
	public UserDTO getByUsername(String userName) {
		
		List<User> users = userRepo.findAll();
		
		User user = users.stream().filter(x->x.getUser_unique_id().equalsIgnoreCase(userName)).findFirst().orElse(null);
		
		UserDTO dto = new UserDTO();
		dto.setDate_created(user.getDate_created());
		dto.setDate_modified(user.getDate_modified());
		dto.setFirst_name(user.getFirst_name());
		dto.setLast_name(user.getLast_name());
		dto.setMail(user.getMail());
		dto.setPassword(user.getPassword());
		dto.setRole(user.getRole());
		dto.setRole_id(user.getRole_id());
		dto.setSalt(user.getSalt());
		dto.setUser_id(user.getUser_id());
		dto.setUsername(user.getUser_unique_id());
		
		
		return dto;
	}


}
