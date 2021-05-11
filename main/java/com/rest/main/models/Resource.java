package com.rest.main.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "resources")
public class Resource {

	@Id
	@Column(name = "resource_id", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int resource_id;

	@Column(name = "role_id", nullable = false)
	public int role_id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "role_foreign_key", referencedColumnName = "id")
	public Role role;

	@Column(name = "resource_name", nullable = false)
	public String resource_name;

	@Column(name = "date_created", nullable = false)
	public Date date_created;

	@Column(name = "date_modified", nullable = false)
	public Date date_modified;

	public int getResource_id() {
		return resource_id;
	}

	public void setResource_id(int resource_id) {
		this.resource_id = resource_id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public int getRole_id() {
		return this.role_id;
	}

	public void setRole_id(int id) {
		this.role_id = id;
	}

	public String getResource_name() {
		return resource_name;
	}

	public void setResource_name(String resource_name) {
		this.resource_name = resource_name;
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

}

