package com.rest.repository.main.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.main.models.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer>{

}
