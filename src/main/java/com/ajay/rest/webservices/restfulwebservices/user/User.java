package com.ajay.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;


@Entity(name="user_details")
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	
	@JsonProperty("User_Name")
	@Size(min=2, message = "Name should have atleast 2 characters")   // Validation utility provided by jakarta
	private String name;
	
	@Past(message = "Birthdate should always be in past")      // Validation utility provided by jakarta
	private LocalDate birthDate;
	
	@OneToMany(mappedBy = "user")// One user can have multiple posts so one to many relation
	@JsonIgnore
	private List<Post> posts;
	
	protected User()
	{
		
	}
	
	public User(Integer id, String name, LocalDate birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}

}
