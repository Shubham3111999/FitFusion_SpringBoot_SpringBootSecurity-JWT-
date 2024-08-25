package com.CN.FitFusion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CN.FitFusion.model.Diet;
import com.CN.FitFusion.model.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Long>{

}
