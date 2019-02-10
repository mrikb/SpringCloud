package com.example.demo.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
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
	private UserDaoService userDao;
	
	@GetMapping("/users")
	public List<User> retrieveAllUser() {
		return userDao.findAll();
	}
	
	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		User user = userDao.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("Id-"+ id);
		}
		
		Resource<User> resource = new Resource<User>(user);
		
		//dynamic url info
		ControllerLinkBuilder linkTo =
				linkTo(methodOn(this.getClass()).retrieveAllUser());
		resource.add(linkTo.withRel("all-users"));

		
		return resource;
	}
	
	@DeleteMapping("/users/{id}")
	public void delUser(@PathVariable int id) {
		User user = userDao.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("Id-"+ id);
		}
		
		
		userDao.delById(id);
//		hateos

	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userDao.save(user);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
}
