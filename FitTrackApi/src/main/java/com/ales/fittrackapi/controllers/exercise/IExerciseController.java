package com.ales.fittrackapi.controllers.exercise;

import com.ales.fittrackapi.entities.Exercise;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercises")
public interface IExerciseController {

    @GetMapping
    List<Exercise> findAll();

    @GetMapping("/{id}")
    Exercise findById(@PathVariable Long id);

    @GetMapping("/likeExample")
    List<Exercise> findAllByExample(@RequestBody Exercise exercise);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Exercise save(@RequestBody Exercise exercise);

    @PostMapping("/saveList")
    @ResponseStatus(HttpStatus.CREATED)
    List<Exercise> saveAll(@RequestBody List<Exercise> exercises);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id);

    @DeleteMapping("/likeExample")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteByExample(@RequestBody Exercise exercise);

    @PutMapping
    Exercise update(@RequestBody Exercise exercise);
}
