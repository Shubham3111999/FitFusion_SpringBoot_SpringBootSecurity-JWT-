package com.CN.FitFusion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CN.FitFusion.dto.ExerciseDto;
import com.CN.FitFusion.exception.ExerciseNotFoundException;
import com.CN.FitFusion.model.Exercise;
import com.CN.FitFusion.model.User;
import com.CN.FitFusion.repository.ExerciseRepository;
import com.CN.FitFusion.repository.UserRepository;

@Service
public class ExerciseService {
	
	@Autowired
	ExerciseRepository exerciseRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;

	public List<Exercise> getAllExercise() {
		return exerciseRepository.findAll();
	}

	public Exercise getExerciseById(Long id) {
		Exercise exercise=null;
		
		if(exerciseRepository.findById(id).isPresent()) {
			exercise=exerciseRepository.findById(id).get();
		}else {
			throw new ExerciseNotFoundException("Not Found");
		}
		
		return exercise;
	}

	public void createExerciseForUser(ExerciseDto exerciseDto, Long userId) {
		User user=userService.getUserById(userId);
		
		Exercise ex=new Exercise();
		ex.setDescription(exerciseDto.getDescription());
		ex.setName(exerciseDto.getName());
		ex.setReps(exerciseDto.getReps());
		ex.setSets(exerciseDto.getSets());
		ex.setUser(user);
		
		ex=exerciseRepository.save(ex);
		
		user.getExerciseList().add(ex);
		
		userRepository.save(user);
	}

	public Exercise updateExercise(ExerciseDto exerciseDto, Long id) {
		Exercise ex=getExerciseById(id);
		ex.setDescription(exerciseDto.getDescription());
		ex.setName(exerciseDto.getName());
		ex.setReps(exerciseDto.getReps());
		ex.setSets(exerciseDto.getSets());
		
		return exerciseRepository.save(ex);
	}

	public void DeleteExercise(Long id) {
		Exercise ex=getExerciseById(id);
		exerciseRepository.delete(ex);
	}
	
	
}
