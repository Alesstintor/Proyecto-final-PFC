package com.ales.fittrackapi.controllers.routine;

import com.ales.fittrackapi.entities.Routine;
import org.springframework.web.bind.annotation.*;

import java.util.List;

interface IRoutineController {

    List<Routine> findAll();

    Routine findById(@PathVariable Long id);

    List<Routine> findAllByExample(@RequestBody Routine routine);

    Routine save(@RequestBody Routine routine);

    List<Routine> saveAll(@RequestBody List<Routine> routines);

    void deleteById(@PathVariable Long id);

    void deleteByExample(@RequestBody Routine routine);

    Routine update(@RequestBody Routine routine);
}
