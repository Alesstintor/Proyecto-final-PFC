package com.ales.fittrackapi.controllers;

import com.ales.fittrackapi.entities.Routine;
import com.ales.fittrackapi.services.IRoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routine")
public class RoutineControllerImpl implements IRoutineController{

    @Autowired
    IRoutineService routineService;

    @GetMapping("/all")
    public List<Routine> findAll() {
        return routineService.findAll();
    }

    @GetMapping("/{id}")
    public Routine findById(@PathVariable Long id) {
        return routineService.findById(id);
    }

    @GetMapping("/likeExample")
    public List<Routine> findAllByExample(@RequestBody Routine routine) {
        return routineService.findAllByExample(routine);
    }

    @PostMapping
    public Routine save(@RequestBody Routine routine) {
        return routineService.save(routine);
    }

    @PostMapping("/saveList")
    public List<Routine> saveAll(@RequestBody List<Routine> routines) {
        return routineService.saveAll(routines);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        return routineService.deleteById(id);
    }

    @DeleteMapping("/delete/likeExample")
    public String deleteByExample(@RequestBody Routine routine) {
        return routineService.deleteByExample(routine);
    }

    @PutMapping
    public Routine update(@RequestBody Routine routine) {
        return routineService.update(routine);
    }

}
