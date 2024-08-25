package com.CN.FitFusion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CN.FitFusion.dto.DietDto;
import com.CN.FitFusion.exception.DietNotFoundException;
import com.CN.FitFusion.model.Diet;
import com.CN.FitFusion.model.User;
import com.CN.FitFusion.repository.DietRepository;
import com.CN.FitFusion.repository.UserRepository;

@Service
public class DietService {
	
	@Autowired
	DietRepository dietRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;

	public List<Diet> getAllDiet() {
		return dietRepository.findAll();
	}

	public Diet getDietById(Long id) {
		Diet diet=null;
		
		if(dietRepository.findById(id).isPresent()) {
			diet=dietRepository.findById(id).get();
		}else {
			throw new DietNotFoundException("Diet not found");
		}
		
		return diet;
	}

	public void createDietForUser(DietDto dietDto, Long userId) {
		User user=userService.getUserById(userId);
		
		Diet diet=new Diet();
		diet.setDescription(dietDto.getDescription());
		diet.setName(dietDto.getName());
		diet.setUser(user);
		
		diet=dietRepository.save(diet);
		
		user.getDiets().add(diet);
		
		userRepository.save(user);
		
	}

	public Diet updateDiet(DietDto dietDto, Long id) {
		Diet diet=getDietById(id);
		diet.setDescription(dietDto.getDescription());
		diet.setName(dietDto.getName());
		return dietRepository.save(diet);
	}

	public void DeleteDiet(Long id) {
		Diet diet=getDietById(id);
		dietRepository.delete(diet);
	}

}
