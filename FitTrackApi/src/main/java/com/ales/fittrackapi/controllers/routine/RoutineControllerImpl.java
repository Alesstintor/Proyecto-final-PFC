package com.ales.fittrackapi.controllers.routine;

import com.ales.fittrackapi.entities.Routine;
import com.ales.fittrackapi.services.routine.IRoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
public class RoutineControllerImpl implements IRoutineController {


    private final IRoutineService routineService;

    @Override
    public List<Routine> findAll() {
        return routineService.findAll();
    }

    @Override
    public Routine findById(@PathVariable Long id) {
        return routineService.findById(id);
    }

    @Override
    public List<Routine> findAllByExample(@RequestBody Routine routine) {
        return routineService.findAllByExample(routine);
    }

    @Override
    public Routine save(@RequestBody Routine routine) {
        return routineService.save(routine);
    }

    @Override
    public List<Routine> saveAll(@RequestBody List<Routine> routines) {
        return routineService.saveAll(routines);
    }

    @Override
    public void deleteById(@PathVariable Long id) {
        routineService.deleteById(id);
    }

    @Override
    public void deleteByExample(@RequestBody Routine routine) {
        routineService.deleteByExample(routine);
    }

    @Override
    public Routine update(@RequestBody Routine routine) {
        System.out.println(routine);
        return routineService.update(routine);
    }

}
