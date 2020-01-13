package com.utsav.springboot.restfuldemo.user;

import java.net.URI;
import java.util.List;

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
public class UserResource {
	
	@Autowired
	private UserDaoService userDaoService;

	@GetMapping(value = "/users")
	public List<User> getAllUsers() {
		return userDaoService.getAllUsers();
	}
	
	@GetMapping(value = "/users/{id}")
	public EntityModel<User> getUser(@PathVariable Integer id) {
		User user = userDaoService.findUser(id);
		if(user == null) {
			throw new UserNotFoundException("ID: " + id);
		}
		EntityModel<User> entityModel = new EntityModel<User>(user);
		WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
		entityModel.add(linkTo.withRel("all-users:"));
		return entityModel;
	}
	
	@DeleteMapping(value = "/users/{id}")
	public User deleteUser(@PathVariable Integer id) {
		User user = userDaoService.deleteUser(id);
		if(user == null) {
			throw new UserNotFoundException("ID: " + id);
		}
		return user;
	}
	
//	@PostMapping(value = "/users")
//	public User getUser(@RequestBody User user) {
//		return userDaoService.save(user);
//	}
	
	@PostMapping(value = "/users")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
		userDaoService.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
}
