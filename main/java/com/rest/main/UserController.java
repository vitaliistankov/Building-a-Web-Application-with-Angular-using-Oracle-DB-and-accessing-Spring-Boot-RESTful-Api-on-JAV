package com.rest.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.main.DTOs.AuthRequestDTO;
import com.rest.main.DTOs.UserDTO;
import com.rest.main.security.JwtTokenProvider;
import com.rest.services.main.implementations.UserService;

@RestController
@RequestMapping("/user")
@Transactional
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager auth;
	@Autowired
	private JwtTokenProvider provider;


	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<Object, Object>> login(@RequestBody AuthRequestDTO request) {

		try {

			UserDTO userDTO = userService.getByUsername(request.getUsername());

			String userName = request.getUsername();

			String pass = request.getPassword();

			List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();

			roles.add(userDTO.getRole());

			String token = provider.create(request.getUsername(), roles);
			
			System.out.println(token);


			Map<Object, Object> response = new HashMap<Object, Object>();

			response.put("username", request.getUsername());
			response.put("token", token);

			try {

				auth.authenticate(new UsernamePasswordAuthenticationToken(userName, pass, roles));

			} catch (AuthenticationException e) {

				System.out.println(e.getMessage());
				System.out.println(e.getStackTrace());

			}

		return ResponseEntity.ok(response);

		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalid username or password!");
		}

	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	void create(@RequestBody UserDTO userDTO) {

		userService.create(userDTO);

	}

	@PostMapping("/update/{id}")
	void update(@RequestBody UserDTO userDTO, @PathVariable int id) {

		userService.update(userDTO, id);
	}
	
	@GetMapping("/get/{userId}")
	UserDTO getById(@PathVariable int userId) {
		UserDTO dto = userService.getById(userId);
		return dto;
	}

	@GetMapping("/getAll")
	List<UserDTO> getAll() {
		List<UserDTO> userDTOList = userService.getAll();
		return userDTOList;
	}

	@DeleteMapping("/delete/{userId}")
	boolean deleteById(@PathVariable int userId) {
		boolean flag = userService.deleteById(userId);
		return flag;
	}

}
