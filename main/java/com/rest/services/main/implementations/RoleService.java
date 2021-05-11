package com.rest.services.main.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.main.DTOs.RoleDTO;
import com.rest.main.models.Role;
import com.rest.repository.main.interfaces.RoleRepository;
import com.rest.service.main.interfaces.RoleServiceInterface;

@Service
@Transactional(readOnly = true)
public class RoleService implements RoleServiceInterface {

	@Autowired(required = true)
	private RoleRepository repo;

	@Override
	public void create(RoleDTO roleDTO) {

		try {

			Role role = new Role();

			role.setRole_name(roleDTO.getRole_name());
			role.setDate_created(roleDTO.getDate_created());
			role.setDate_modified(roleDTO.getDate_modified());

			repo.save(role);

		} catch (Exception e) {

			System.out.println(e.getMessage());
			e.getStackTrace();
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void update(RoleDTO roleDTO, int id) {

		try {

			Optional<Role> role = repo.findById(id);

			role.get().setDate_created(roleDTO.getDate_created());
			role.get().setDate_modified(roleDTO.getDate_modified());
			roleDTO.setRole_name(roleDTO.getRole_name());

			repo.save(role.get());

		} catch (Exception e) {

			System.out.println(e.getMessage());
			e.getStackTrace();
		}

	}

	@Override
	@Transactional(readOnly = false)
	public RoleDTO getById(int roleId) {

		try {

			Optional<Role> role = repo.findById(roleId);

			RoleDTO dto = new RoleDTO();

			dto.setDate_created(role.get().getDate_created());
			dto.setDate_modified(role.get().getDate_modified());
			dto.setRole_name(role.get().getRole_name());
			dto.setId(role.get().getId());

			return dto;

		} catch (Exception e) {

			System.out.println(e.getMessage());
			e.getStackTrace();
		}

		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public List<RoleDTO> getAll() {

		try {

			List<Role> role = repo.findAll();

			List<RoleDTO> dto = new ArrayList<RoleDTO>();

			for (int i = 0; i < role.size(); i++) {

				RoleDTO dto2 = new RoleDTO();

				dto2.setDate_created(role.get(i).getDate_created());
				dto2.setDate_modified(role.get(i).getDate_modified());
				dto2.setRole_name(role.get(i).getRole_name());
				dto2.setId(role.get(i).getId());

				dto.add(dto2);

			}

			return dto;

		} catch (Exception e) {

			System.out.println(e.getMessage());
			e.getStackTrace();
		}

		return null;

	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteById(int roleId) {

		try {

			repo.deleteById(roleId);

			return true;

		} catch (Exception e) {

			System.out.println(e.getMessage());
			e.getStackTrace();
		}
		
		return true;
	}

}
