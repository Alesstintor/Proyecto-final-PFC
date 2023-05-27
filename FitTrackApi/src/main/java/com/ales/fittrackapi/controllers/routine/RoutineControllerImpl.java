package com.ales.fittrackapi.controllers.routine;

import com.ales.fittrackapi.entities.Routine;
import com.ales.fittrackapi.services.routine.IRoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routines")
@RequiredArgsConstructor
public class RoutineControllerImpl implements IRoutineController {


    private final IRoutineService routineService;

    @GetMapping
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
    @ResponseStatus(HttpStatus.CREATED)
    public Routine save(@RequestBody Routine routine) {
        return routineService.save(routine);
    }

    @PostMapping("/saveList")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Routine> saveAll(@RequestBody List<Routine> routines) {
        return routineService.saveAll(routines);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteById(@PathVariable Long id) {
        routineService.deleteById(id);
    }

    @DeleteMapping("/likeExample")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteByExample(@RequestBody Routine routine) {
        routineService.deleteByExample(routine);
    }

    @PutMapping
    public Routine update(@RequestBody Routine routine) {
        System.out.println(routine);
        return routineService.update(routine);
    }

}
