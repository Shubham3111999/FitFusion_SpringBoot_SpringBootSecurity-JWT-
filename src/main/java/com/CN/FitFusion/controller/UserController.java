package com.CN.FitFusion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.CN.FitFusion.dto.UserDto;
import com.CN.FitFusion.model.Diet;
import com.CN.FitFusion.model.Exercise;
import com.CN.FitFusion.model.User;
import com.CN.FitFusion.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> getAllUser(){
		return userService.getAllUser();
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public User getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public User updateUser(@RequestBody UserDto userDto, @PathVariable Long id) {
		return userService.updateUser(userDto,id);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void DeleteUser(@PathVariable Long id) {
		userService.DeleteUser(id);
	}
	
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/exercise/{id}")
	@PreAuthorize("hasRole('CUSTOMER')")
	public List<Exercise> getAllExerciseForUser(@PathVariable Long id){
		return userService.getAllExerciseForUser(id);
	}
	
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/diet/{id}")
	@PreAuthorize("hasRole('CUSTOMER')")
	public List<Diet> getAllDietForUser(@PathVariable Long id){
		return userService.getAllDietForUser(id);
	}
	
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/register")
	public User createUser(@RequestBody UserDto userDto) {
		return userService.createUser(userDto);
		
	}
	
	
}
