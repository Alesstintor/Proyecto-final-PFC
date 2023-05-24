package com.ales.fittrackapi.controllers;

import com.ales.fittrackapi.entities.Routine;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routines")
interface IRoutineController {

    @GetMapping
    List<Routine> findAll();

    @GetMapping("/{id}")
    Routine findById(@PathVariable Long id);

    @GetMapping("/likeExample")
    List<Routine> findAllByExample(@RequestBody Routine routine);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Routine save(@RequestBody Routine routine);

    @PostMapping("/saveList")
    @ResponseStatus(HttpStatus.CREATED)
    List<Routine> saveAll(@RequestBody List<Routine> routines);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id);

    @DeleteMapping("/likeExample")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteByExample(@RequestBody Routine routine);

    @PutMapping
    Routine update(@RequestBody Routine routine);
}
