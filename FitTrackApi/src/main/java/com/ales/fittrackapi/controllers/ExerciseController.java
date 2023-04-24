package com.ales.fittrackapi.controllers;

import com.ales.fittrackapi.entities.Exercise;
import com.ales.fittrackapi.services.IExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {

    @Autowired
    IExerciseService exerciseService;

    @GetMapping("/all")
    public List<Exercise> findAll() {
        return exerciseService.findAll();
    }

    @GetMapping("/{id}")
    public Exercise findById(@PathVariable Long id) {
        return exerciseService.findById(id);
    }

    @GetMapping("/likeExample")
    public List<Exercise> findAllByExample(@RequestBody Exercise exercise) {
        return exerciseService.findAllByExample(exercise);
    }

    @PostMapping
    public Exercise save(@RequestBody Exercise exercise) {
        return exerciseService.save(exercise);
    }

    @PostMapping("/saveList")
    public List<Exercise> saveAll(@RequestBody List<Exercise> exercises) {
        return exerciseService.saveAll(exercises);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        return exerciseService.deleteById(id);
    }

    @DeleteMapping("/delete/likeExample")
    public String deleteByExample(@RequestBody Exercise exercise) {
        return exerciseService.deleteByExample(exercise);
    }

    @PutMapping
    public String update(@RequestBody Exercise exercise) {
        return exerciseService.update(exercise);
    }

}
