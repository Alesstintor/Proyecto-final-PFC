package com.ales.fittrackapi.controllers.exercise;

import com.ales.fittrackapi.entities.Exercise;
import com.ales.fittrackapi.services.exercise.IExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
public class ExerciseControllerImpl implements IExerciseController {

    private final IExerciseService exerciseService;

    @Override
    public List<Exercise> findAll() {
        return exerciseService.findAll();
    }

    @Override
    public Exercise findById(@PathVariable Long id) {
        return exerciseService.findById(id);
    }

    @Override
    public List<Exercise> findAllByExample(@RequestBody Exercise exercise) {
        return exerciseService.findAllByExample(exercise);
    }

    @Override
    public Exercise save(@RequestBody Exercise exercise) {
        return exerciseService.save(exercise);
    }

    @Override
    public List<Exercise> saveAll(@RequestBody List<Exercise> exercises) {
        return exerciseService.saveAll(exercises);
    }

    @Override
    public void deleteById(@PathVariable Long id) {
        exerciseService.deleteById(id);
    }

    @Override
    public void deleteByExample(@RequestBody Exercise exercise) {
        exerciseService.deleteByExample(exercise);
    }

    @Override
    public Exercise update(@RequestBody Exercise exercise) {
        return exerciseService.update(exercise);
    }

}
