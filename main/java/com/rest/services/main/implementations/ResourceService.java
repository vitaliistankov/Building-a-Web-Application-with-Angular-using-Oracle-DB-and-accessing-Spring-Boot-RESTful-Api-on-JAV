package com.rest.services.main.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.main.DTOs.ResourceDTO;
import com.rest.main.models.Resource;
import com.rest.main.models.Role;
import com.rest.repository.main.interfaces.ResourceRepository;
import com.rest.repository.main.interfaces.RoleRepository;
import com.rest.service.main.interfaces.ResourceServiceInterface;

@Service
@Transactional(readOnly = true)
public class ResourceService implements ResourceServiceInterface {

	@Autowired
	ResourceRepository repo;

	@Autowired
	RoleRepository roleRepo;

	@Override
	@Transactional(readOnly = false)
	public void create(ResourceDTO dto) {

		try {

			Resource res = new Resource();

			Optional<Role> role = roleRepo.findById(dto.getRole_id());

			res.setDate_created(dto.getDate_created());

			res.setDate_modified(dto.getDate_modified());

			res.setResource_name(dto.getResource_name());

			res.setRole_id(dto.getRole_id());

			res.setRole(role.get());

			repo.save(res);

		} catch (Exception e) {

			System.out.println(e.getMessage());

			e.getStackTrace();
		}

	}

	@Override
	@Transactional(readOnly = false)
	public void update(ResourceDTO resDTO, int id) {

		try {

			Optional<Resource> res = repo.findById(id);

			res.get().setDate_created(resDTO.getDate_created());
			res.get().setDate_modified(resDTO.getDate_modified());
			res.get().setResource_name(resDTO.getResource_name());
			res.get().setRole_id(resDTO.getRole_id());

			if (res.get().getRole().getId() != resDTO.getRole_id()) {

				Optional<Role> role = roleRepo.findById(resDTO.getRole_id());

				res.get().setRole(role.get());

			}

			repo.saveAndFlush(res.get());
		} catch (Exception e) {

			System.out.println(e.getMessage());
			e.getStackTrace();
		}

	}

	@Override
	@Transactional(readOnly = false)
	public ResourceDTO getById(int id) {

		try {

			Optional<Resource> resource = repo.findById(id);

			ResourceDTO dto = new ResourceDTO();

			dto.setDate_created(resource.get().getDate_created());
			dto.setDate_modified(resource.get().getDate_modified());
			dto.setResource_name(resource.get().getResource_name());
			dto.setRole_id(resource.get().getRole_id());

			return dto;

		} catch (Exception e) {

			System.out.println(e.getMessage());
			e.getStackTrace();
		}

		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public List<ResourceDTO> getAll() {

		try {

			List<Resource> resources = repo.findAll();

			List<ResourceDTO> dtos = new ArrayList<ResourceDTO>();

			for (int i = 0; i < resources.size(); i++) {

				ResourceDTO d = new ResourceDTO();

				Optional<Role> role = roleRepo.findById(resources.get(i).getRole_id());

				d.setDate_created(resources.get(i).getDate_created());
				d.setDate_modified(resources.get(i).getDate_modified());
				d.setResource_name(resources.get(i).getResource_name());
				d.setRole_id(resources.get(i).getRole_id());
				d.setRole(role.get());

				dtos.add(d);

			}

			return dtos;

		} catch (Exception e) {

			System.out.println(e.getMessage());
			e.getStackTrace();
		}

		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteById(int id) {

		try {

			repo.deleteById(id);

			return false;

		} catch (Exception e) {

			System.out.println(e.getMessage());
			e.getStackTrace();
		}

		return true;
	}

}
