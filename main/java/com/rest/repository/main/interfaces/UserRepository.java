package com.rest.repository.main.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.main.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
