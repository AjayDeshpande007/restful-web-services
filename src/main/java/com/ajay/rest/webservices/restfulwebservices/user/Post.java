package com.ajay.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

@Entity
public class Post {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min = 8)
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)      // because many posts can have by one user
	@JsonIgnore
	private User user;
	
	public Post() {
		
	}
	
	public Post(Integer id, String description) {
		super();
		this.id = id;
		this.description = description;
		
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", description=" + description + "]";
	}

	

}
