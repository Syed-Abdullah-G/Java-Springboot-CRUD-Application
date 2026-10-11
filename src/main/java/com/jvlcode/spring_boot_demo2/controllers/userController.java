package com.jvlcode.spring_boot_demo2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jvlcode.spring_boot_demo2.entity.UserEntity;
import com.jvlcode.spring_boot_demo2.exceptions.ResourceNotFoundException;
import com.jvlcode.spring_boot_demo2.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class userController {
	
	@Autowired
	private UserRepository userRepository;
	
//	@GetMapping
//	public String getUsers() {
//		return "Hello Syed Abdullah";
//	}
//	
	@GetMapping
	public List<UserEntity> getUsers() {
//		return Arrays.asList(new User(1L, "Ram", "Ram@gmail.com"), new User(2L, "Joe", "Joe@gmail.com"),new User(3L, "Alice", "Alice@gmail.com"));
	
	return userRepository.findAll();
	
	
	}
	
	@PostMapping
	public UserEntity createUser(@RequestBody UserEntity user) {
		return userRepository.save(user);
	
	}
	
	@GetMapping("/{id}")
	public UserEntity getUserById(@PathVariable Long id ) {
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with this id: "+id));
	}
	
	@PutMapping("/{id}")
	public UserEntity updateUser(@PathVariable Long id,@RequestBody UserEntity user) {
		UserEntity userData =  userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with this id: "+id));
		userData.setEmail(user.getEmail());
		userData.setName(user.getName());
		return userRepository.save(userData);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		UserEntity userData =  userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with this id: "+id));
		userRepository.delete(userData);
		return ResponseEntity.ok().build();
	}
	
	
	
	
	
	
	
}
