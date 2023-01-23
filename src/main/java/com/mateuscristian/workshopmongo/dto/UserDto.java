package com.mateuscristian.workshopmongo.dto;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import com.mateuscristian.workshopmongo.entities.User;

public class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String name;
	private String email;


	public UserDto() {}
	
	public UserDto(User obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
