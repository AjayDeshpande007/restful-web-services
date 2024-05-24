package com.ajay.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.hibernate.metamodel.mapping.EntityValuedModelPart;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ajay.rest.webservices.restfulwebservices.jpa.UserRepository;

import jakarta.validation.Valid;


@RestController
public class UserJpaResource {
	
	
	
	private UserRepository repository;
	private PostRepository postRepository;
	
	public UserJpaResource( UserRepository repository, PostRepository postRepository)
	{
		this.postRepository=postRepository;
		this.repository=repository;
	}
	
	//Retrieve all users
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers()
	{
		return repository.findAll();
	}

	//Retrieve a user by ID
	@GetMapping("/jpa/users/{id}")
	public User retrieveUser(@PathVariable int id){
		Optional<User> user = repository.findById(id);
		
		if (user.isEmpty())
		{
			throw new UserNotFoundException("id: "+id);
		}
		
		//EntityModel<User> entityModel = EntityValuedModelPart.of(user.get());
		
		return user.get();
	}
	
	
	//Add a user
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user)
	{
		User savedUser = repository.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();   
		// To return HTTP response 201 -> Created
		return ResponseEntity.created(location).build();
	}
	
	
	//Delete a user by ID
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id){
		repository.deleteById(id);
	}
	
	//-------------------------------------------------------------------------------------------------------
	
	
	// API for retrieve all the posts from DB for a Specific User
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrievePostsForUser(@PathVariable int id){
		
		Optional<User> user = repository.findById(id);
		
		if(user.isEmpty()) {
			throw new UserNotFoundException("Id: "+id);
		}
		
		return user.get().getPosts();
	}
	
	
	// API for Create a post (and insert that into DB) for a Specific User
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id, @RequestBody Post post){
		
		Optional<User> user = repository.findById(id);
		
		if(user.isEmpty()) {
			throw new UserNotFoundException("Id: " + id);
		}
		post.setUser(user.get());
		
		Post savedPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().
						path("/{id}").buildAndExpand(savedPost.getId()).
						toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	
	// API for retrieve a specific post for specific user
	@GetMapping("/jpa/users/{userId}/posts/{postId}")
	public Post retrievePostForUserById(@PathVariable int userId, @PathVariable int postId){
		
		Optional<User> user = repository.findById(userId);
		
		if(user.isEmpty()) {
			throw new UserNotFoundException("Id: " + userId);
		}
		
		Optional<Post> post = postRepository.findById(postId);
		
		if(post.isEmpty()) {
			throw new PostNotFoundException("Id: " + postId);
		}
		
		return post.get();
		
	}
	
}
