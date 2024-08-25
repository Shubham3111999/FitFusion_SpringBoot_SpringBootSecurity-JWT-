package com.CN.FitFusion.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.CN.FitFusion.dto.UserDto;
import com.CN.FitFusion.exception.UserNotFoundException;
import com.CN.FitFusion.model.Diet;
import com.CN.FitFusion.model.Exercise;
import com.CN.FitFusion.model.Role;
import com.CN.FitFusion.model.User;
import com.CN.FitFusion.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	public User getUserById(Long id) {
		User user = null;
		if (userRepository.findById(id).isPresent()) {
			user = userRepository.findById(id).get();
		} else {
			throw new UserNotFoundException("User not found");
		}
		return user;
	}

	public User updateUser(UserDto userDto, Long id) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(userDto.getPassword());

		User user = getUserById(id);

		user.setEmail(userDto.getEmail());
		user.setPassword(encodedPassword); // encode password
		user.setAge(userDto.getAge());
		user.setGender(userDto.getGender());
		user.setContactNo(userDto.getContactNo());

		Set<Role> roles = new HashSet<>();
		Role role = new Role();

		if (userDto.getUserType() != null) {
			if (userDto.getUserType().equalsIgnoreCase("ADMIN")) {
				role.setRoleName("ROLE_ADMIN"); // set role
				roles.add(role);
				user.setRoles(roles);
			} else if (userDto.getUserType().equalsIgnoreCase("CUSTOMER")) {
				role.setRoleName("ROLE_CUSTOMER");
				roles.add(role);
				user.setRoles(roles);

			} else if (userDto.getUserType().equalsIgnoreCase("TRAINER")) {
				role.setRoleName("ROLE_TRAINER");
				roles.add(role);
				user.setRoles(roles);
			}
		}

		return userRepository.save(user);
	}

	public void DeleteUser(Long id) {
		User user = getUserById(id);
		userRepository.delete(user);
	}

	public List<Exercise> getAllExerciseForUser(Long id) {
		User user = getUserById(id);
		return user.getExerciseList();
	}

	public List<Diet> getAllDietForUser(Long id) {
		User user = getUserById(id);
		return user.getDiets();
	}

	public User createUser(UserDto userDto) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(userDto.getPassword());
		
		User user = new User();
		user.setEmail(userDto.getEmail());
		user.setPassword(encodedPassword); // encode password
		user.setAge(userDto.getAge());
		user.setGender(userDto.getGender());
		user.setContactNo(userDto.getContactNo());
		
		Set<Role> roles = new HashSet<>();
		Role role = new Role();

		if (userDto.getUserType() != null) {
			if (userDto.getUserType().equalsIgnoreCase("ADMIN")) {
				role.setRoleName("ROLE_ADMIN"); // set role
				roles.add(role);
				user.setRoles(roles);
			} else if (userDto.getUserType().equalsIgnoreCase("CUSTOMER")) {
				role.setRoleName("ROLE_CUSTOMER");
				roles.add(role);
				user.setRoles(roles);

			} else if (userDto.getUserType().equalsIgnoreCase("TRAINER")) {
				role.setRoleName("ROLE_TRAINER");
				roles.add(role);
				user.setRoles(roles);
			}
		}

		return userRepository.save(user);
	}

}
