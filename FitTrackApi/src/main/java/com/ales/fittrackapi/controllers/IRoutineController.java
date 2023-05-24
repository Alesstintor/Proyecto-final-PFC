package com.ales.fittrackapi.controllers;

import com.ales.fittrackapi.entities.Routine;
import org.springframework.web.bind.annotation.*;

import java.util.List;

interface IRoutineController {

    @GetMapping("/all")
    List<Routine> findAll();

    @GetMapping("/{id}")
    Routine findById(@PathVariable Long id);

    @GetMapping("/likeExample")
    List<Routine> findAllByExample(@RequestBody Routine routine);

    @PostMapping
    Routine save(@RequestBody Routine routine);

    @PostMapping("/saveList")
    List<Routine> saveAll(@RequestBody List<Routine> routines);

    @DeleteMapping("/delete/{id}")
    String deleteById(@PathVariable Long id);

    @DeleteMapping("/delete/likeExample")
    String deleteByExample(@RequestBody Routine routine);

    @PutMapping
    Routine update(@RequestBody Routine routine);
}
