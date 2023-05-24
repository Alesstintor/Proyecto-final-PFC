package com.ales.fittrackapi.services;

import com.ales.fittrackapi.entities.Exercise;
import com.ales.fittrackapi.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        Exercise exerciseToDelete = findById(id);
        if (exerciseToDelete == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No exercise found with id: " + id) ;

        exerciseRepository.deleteById(id);
        return "Exercise with id: " + id + ", deleted successfully";
    }

    @Override
    public String deleteByExample(Exercise exercise) {
        List<Exercise> exercisesToDelete = findAllByExample(exercise);
        if (exercisesToDelete.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No exercise was found with given example");

        exerciseRepository.deleteAll(exercisesToDelete);
        return exercisesToDelete.size() + " exercises deleted successfully";
    }


    @Override
    public Exercise update(Exercise exercise) {
        Exercise exerciseToUpdate = findById(exercise.getId());
        if (exerciseToUpdate == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No exercise found with id " + exercise.getId());

        return exerciseRepository.save(exercise);
    }
}
