package com.ales.fittrackapi.controllers;

import com.ales.fittrackapi.entities.Exercise;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IExerciseController {

    @GetMapping("/all")
    List<Exercise> findAll();

    @GetMapping("/{id}")
    Exercise findById(@PathVariable Long id);

    @GetMapping("/likeExample")
    List<Exercise> findAllByExample(@RequestBody Exercise exercise);

    @PostMapping
    Exercise save(@RequestBody Exercise exercise);

    @PostMapping("/saveList")
    List<Exercise> saveAll(@RequestBody List<Exercise> exercises);

    @DeleteMapping("/delete/{id}")
    String deleteById(@PathVariable Long id);

    @DeleteMapping("/delete/likeExample")
    String deleteByExample(@RequestBody Exercise exercise);

    @PutMapping
    Exercise update(@RequestBody Exercise exercise);
}
