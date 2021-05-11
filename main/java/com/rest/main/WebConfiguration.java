package com.rest.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.rest.services.main.implementations.ResourceService;
import com.rest.services.main.implementations.RoleService;
import com.rest.services.main.implementations.UserService;

@Configuration
@EnableJpaRepositories(basePackages = "com.rest.repository.main.interfaces")
@EnableTransactionManagement
@PropertySource(value = "application.properties")
public class WebConfiguration {

	@Bean
	public UserService userService() {

		return new UserService();

	}

	@Bean
	public RoleService roleService() {

		return new RoleService();

	}

	@Bean
	public ResourceService resourceService() {

		return new ResourceService();

	}

}
