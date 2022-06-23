package sbnz.skincare.dto;

import sbnz.skincare.facts.User;

public class UserDTO {

	private String username;
	private String name;
	private String surname;
	private String email;
	private String role;

	public UserDTO(User user) {

		this.username = user.getUsername();
		this.email = user.getEmail();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.role = user.getRole().getName();
	}

	public UserDTO() {
		super();
	}

	public UserDTO(String username, String name, String surname, String email, String role) {
		super();
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
