package com.CN.FitFusion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.CN.FitFusion.dto.ExerciseDto;
import com.CN.FitFusion.model.Diet;
import com.CN.FitFusion.model.Exercise;
import com.CN.FitFusion.service.ExerciseService;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {
	
	@Autowired
	ExerciseService exerciseService;
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/all")
	@PreAuthorize("hasRole('TRAINER')")
	public List<Exercise> getAllExercise(){
		return exerciseService.getAllExercise();
	}
	
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('TRAINER')")
	public Exercise getExerciseById(@PathVariable Long id) {
		return exerciseService.getExerciseById(id);
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/create/{userId}")
	@PreAuthorize("hasRole('TRAINER')")
	public void createExerciseForUser(@RequestBody ExerciseDto exerciseDto, @PathVariable Long userId) {
		exerciseService.createExerciseForUser(exerciseDto,userId);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('TRAINER')")
	public Exercise updateExercise(@RequestBody ExerciseDto exerciseDto, @PathVariable Long id) {
		return exerciseService.updateExercise(exerciseDto,id);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('TRAINER')")
	public void DeleteExercise(@PathVariable Long id) {
		exerciseService.DeleteExercise(id);
	}
	
	
}
