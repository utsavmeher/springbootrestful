package com.utsav.springboot.restfuldemo.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserJPAResource {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;
	
	@GetMapping(value = "/jpa/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping(value = "/jpa/users/{id}")
	public EntityModel<User> getUser(@PathVariable Integer id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("ID: " + id);
		}
		EntityModel<User> entityModel = new EntityModel<User>(user.get());
		WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
		entityModel.add(linkTo.withRel("all-users:"));
		return entityModel;
	}
	
	@GetMapping(value = "/jpa/users/{id}/posts")
	public List<Post> getUserPosts(@PathVariable Integer id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("ID: " + id);
		}
		return user.get().getPosts();
	}
	
	@PostMapping(value = "/jpa/users/{id}/posts")
	public List<Post> saveUserPosts(@PathVariable Integer id, @RequestBody Post post) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("ID: " + id);
		}
		post.setUser(user.get());
		postRepository.save(post);
		return user.get().getPosts();
	}
	
	@DeleteMapping(value = "/jpa/users/{id}")
	public User deleteUser(@PathVariable Integer id) {
		Optional<User> entity = userRepository.findById(id);
		if (!entity.isPresent()) {
			throw new UserNotFoundException("ID: " + id);
		}
		userRepository.delete(entity.get());
		return entity.get();
	}
	
	@PostMapping(value = "/jpa/users")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
		userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
}
