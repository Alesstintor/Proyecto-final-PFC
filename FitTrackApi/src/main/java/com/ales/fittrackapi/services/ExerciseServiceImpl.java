package com.ales.fittrackapi.services;

import com.ales.fittrackapi.entities.Exercise;
import com.ales.fittrackapi.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceImpl implements IExerciseService{

    @Autowired
    ExerciseRepository exerciseRepository;

    @Override
    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    @Override
    public Exercise findById(Long id) {
        return exerciseRepository.findById(id).orElse(null);
    }

    @Override
    public List<Exercise> findAllByExample(Exercise exercise) {
        return exerciseRepository.findAll(Example.of(exercise));
    }

    @Override
    public Exercise save(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    @Override
    public List<Exercise> saveAll(List<Exercise> exercises) {
        return exerciseRepository.saveAll(exercises);
    }

    @Override
    public String deleteById(Long id) {
        return null;
    }

    @Override
    public String deleteByExample(Exercise exercise) {
        return null;
    }

    @Override
    public String update(Exercise exercise) {
        return null;
    }
}
