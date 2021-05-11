package com.rest.main.DTOs;

import java.sql.Date;

import com.rest.main.models.Role;

public class ResourceDTO {
	
	public int resource_id;
	public int role_id;
	public String resource_name;
	public Date date_modified;
	public Date date_created;
	private Role role;
	
	public Role getRole() {
		return this.role;
	}
	
	public void setRole(Role role) {
		this.role=role;
	}
	
	public int getResource_id() {
		return resource_id;
	}
	public void setResource_id(int resource_id) {
		this.resource_id = resource_id;
	}
	public int getRole_id() {
		return this.role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getResource_name() {
		return resource_name;
	}
	public void setResource_name(String resource_name) {
		this.resource_name = resource_name;
	}
	public Date getDate_modified() {
		return date_modified;
	}
	public void setDate_modified(Date date_modified) {
		this.date_modified = date_modified;
	}
	public Date getDate_created() {
		return date_created;
	}
	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}
	
}
