package com.sevenj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonProperty;

@Entity
@Table(name = "users")
public class User {
	private int userId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private Role role;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="UserID")
	@JsonProperty("userID")
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name="Username", nullable=false)
	@JsonProperty("username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name="Password", nullable=false)
	@JsonProperty("password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="FirstName", nullable=false)
	@JsonProperty("firstName")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name="LastName", nullable=false)
	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@ManyToOne
	@JoinColumn(name="RoleID", nullable=false)
	@JsonProperty("role")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	@Transient
	public String getFullname(){
		return firstName + " " + lastName;
	}

	
}
