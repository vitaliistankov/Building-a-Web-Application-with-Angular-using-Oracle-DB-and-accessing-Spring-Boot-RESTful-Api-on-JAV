package com.rest.main.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int id;
	
	@Column(name = "role_name", nullable = false)
	public String role_name;

	@Column(name = "date_created", nullable = false)
	public Date date_created;

	@Column(name = "date_modified", nullable = false)
	public Date date_modified;

	@JsonIgnore
	@OneToMany(mappedBy = "role", targetEntity = Resource.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Resource> resources = new ArrayList<Resource>();

	@JsonIgnore
	@OneToMany(mappedBy = "role", targetEntity = User.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public List<User> users = new ArrayList<User>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public Date getDate_created() {
		return date_created;
	}

	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}

	public Date getDate_modified() {
		return date_modified;
	}

	public void setDate_modified(Date date_modified) {
		this.date_modified = date_modified;
	}

	@Override
	public String getAuthority() {
		return this.role_name;
	}

	
}

