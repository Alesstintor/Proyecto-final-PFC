package com.ales.fittrackapi.services.exercise;

import com.ales.fittrackapi.entities.Exercise;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IExerciseService {

    List<Exercise> findAll();
    Exercise findById(Long id);
    List<Exercise> findAllByExample(Exercise exercise);
    Exercise save(Exercise exercise);
    List<Exercise> saveAll(List<Exercise> exercises);
    void deleteById(Long id);
    void deleteByExample(Exercise exercise);
    Exercise update(Exercise exercise);
}
