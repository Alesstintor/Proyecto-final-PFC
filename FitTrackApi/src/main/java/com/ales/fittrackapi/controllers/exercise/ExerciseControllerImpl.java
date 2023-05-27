package com.ales.fittrackapi.controllers.exercise;

import com.ales.fittrackapi.entities.Exercise;
import com.ales.fittrackapi.services.exercise.IExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercises")
@RequiredArgsConstructor
public class ExerciseControllerImpl implements IExerciseController {

    private final IExerciseService exerciseService;

    @GetMapping
    public List<Exercise> findAll() {
        return exerciseService.findAll();
    }

    @GetMapping("{id}")
    public Exercise findById(@PathVariable Long id) {
        return exerciseService.findById(id);
    }

    @GetMapping("/likeExample")
    public List<Exercise> findAllByExample(@RequestBody Exercise exercise) {
        return exerciseService.findAllByExample(exercise);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Exercise save(@RequestBody Exercise exercise) {
        return exerciseService.save(exercise);
    }

    @PostMapping("/saveList")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Exercise> saveAll(@RequestBody List<Exercise> exercises) {
        return exerciseService.saveAll(exercises);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        exerciseService.deleteById(id);
    }

    @DeleteMapping("/likeExample")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByExample(@RequestBody Exercise exercise) {
        exerciseService.deleteByExample(exercise);
    }

    @PutMapping
    public Exercise update(@RequestBody Exercise exercise) {
        return exerciseService.update(exercise);
    }

}
