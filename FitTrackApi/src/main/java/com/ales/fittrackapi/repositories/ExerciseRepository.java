package com.ales.fittrackapi.repositories;

import com.ales.fittrackapi.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
