package com.ales.fittrackapi.services;

import com.ales.fittrackapi.entities.Exercise;

import java.util.List;

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
