package com.ales.fittrackapi.controllers.exercise;

import com.ales.fittrackapi.entities.Exercise;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public interface IExerciseController {

    List<Exercise> findAll();

    Exercise findById(@PathVariable Long id);

    List<Exercise> findAllByExample(@RequestBody Exercise exercise);

    Exercise save(@RequestBody Exercise exercise);

    List<Exercise> saveAll(@RequestBody List<Exercise> exercises);

    void deleteById(@PathVariable Long id);

    void deleteByExample(@RequestBody Exercise exercise);

    Exercise update(@RequestBody Exercise exercise);
}
