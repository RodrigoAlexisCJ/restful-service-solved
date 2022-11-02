package com.tcs.User.and.post.rest.service.User;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tcs.User.and.post.rest.service.Jpa.PostRepository;


@RestController
public class UserController {
	@Autowired
	private UserService service;
	@Autowired
	private PostRepository repository;
	@GetMapping(value="/users")
	public List<User> getAllUsers(){
		return service.getAll();
	}
	@GetMapping(value="/users/{id}")
	public User getUserr(@PathVariable int id) {
		User userFound = service.getUser(id);
		if (userFound == null) {
			throw new UserNotFoundException("User not Found id =" + id);
		}
		else
			return userFound;
	}
	@PostMapping(value="/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.createUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	@DeleteMapping(value="/users")
	public void deleteUser(int id) {
		service.deleteUser(id);
	}
	
	@GetMapping(value="/users/{id}/posts") 
	public List<Post> getPosts(@PathVariable int id){
		User userFound = service.getUser(id);
		if (userFound == null) {
			throw new UserNotFoundException("User not Found id =" + id);
		}
		else
			return userFound.getPosts();
	}
	
	@PostMapping(value="/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id,@Valid @RequestBody Post post) {
		User user = service.getUser(id);
		if(user==null) {
			throw new UserNotFoundException("User not Found id =" + id);
		}
		else {
			post.setUser(user);
			Post savedPost = repository.save(post);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(savedPost.getId())
					.toUri();
			return ResponseEntity.created(location).build();
		}
	}
}

