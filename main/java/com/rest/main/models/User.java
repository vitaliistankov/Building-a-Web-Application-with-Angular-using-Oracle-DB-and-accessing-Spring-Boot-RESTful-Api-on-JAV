package com.rest.main.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true)
	public int user_id;

	@Column(name = "date_created", nullable = false)
	public Date date_created;

	@Column(name = "date_modified", nullable = false)
	public Date date_modified;

	@Column(name = "role_id", nullable = false)
	public int role_id;

	@Column(name = "first_name", nullable = true)
	public String first_name;

	@Column(name = "last_name", nullable = true)
	public String last_name;

	@Column(name = "username", nullable = false)
	public String username;

	@Column(name = "password", nullable = false)
	public String password;

	@Column(name = "salt", nullable = false)
	public String salt;

	@Column(name = "mail", nullable = false)
	public String mail;

	@JsonIgnore
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "role_foreign_key", referencedColumnName = "id")
	public Role role;

	

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getUser_unique_id() {
		return this.username;
	}

	public void setUser_unique_id(String user_unique_id) {
		this.username = user_unique_id;
	}

	public Role getRole() {
		return this.role;
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

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

}