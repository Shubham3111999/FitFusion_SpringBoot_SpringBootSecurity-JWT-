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

import com.CN.FitFusion.dto.DietDto;
import com.CN.FitFusion.model.Diet;
import com.CN.FitFusion.service.DietService;

@RestController
@RequestMapping("/diet")
public class DietController {
	
	@Autowired
	DietService dietService;
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/all")
	@PreAuthorize("hasRole('TRAINER')")
	public List<Diet> getAllDiet(){
		return dietService.getAllDiet();
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('TRAINER')")
	public Diet getDietById(@PathVariable Long id) {
		return dietService.getDietById(id);
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/create/{userId}")
	@PreAuthorize("hasRole('TRAINER')")
	public void createDietForUser(@RequestBody DietDto dietDto, @PathVariable Long userId) {
		dietService.createDietForUser(dietDto,userId);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('TRAINER')")
	public Diet updateDiet(@RequestBody DietDto dietDto, @PathVariable Long id) {
		return dietService.updateDiet(dietDto, id);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('TRAINER')")
	public void DeleteDiet(@PathVariable Long id) {
		dietService.DeleteDiet(id);
	}
	
	
}
