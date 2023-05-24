package com.ales.fittrackapi.services;

import com.ales.fittrackapi.entities.Routine;
import com.ales.fittrackapi.repositories.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RoutineServiceImpl implements IRoutineService{

    @Autowired
    RoutineRepository routineRepository;


    @Override
    public List<Routine> findAll() {
        return routineRepository.findAll();
    }

    @Override
    public Routine findById(Long id) {
        return routineRepository.findById(id).orElse(null);
    }

    @Override
    public List<Routine> findAllByExample(Routine routine) {
        return routineRepository.findAll(Example.of(routine));
    }

    @Override
    public Routine save(Routine routine) {
        return routineRepository.save(routine);
    }

    @Override
    public List<Routine> saveAll(List<Routine> routines) {
        return routineRepository.saveAll(routines);
    }

    @Override
    public String deleteById(Long id) {
        Routine routineToDelete = findById(id);
        if (routineToDelete == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No routine found with id: " + id) ;

        routineRepository.deleteById(id);
        return "Record with id: " + id + ", deleted successfully";
    }

    @Override
    public String deleteByExample(Routine routine) {
        List<Routine> routinesToDelete = findAllByExample(routine);
        if (routinesToDelete.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No routine was found with given example");

        routineRepository.deleteAll(routinesToDelete);
        return routinesToDelete.size() + " routines deleted successfully";
    }


    @Override
    public Routine update(Routine routine) {
        Routine routineToUpdate = findById(routine.getId());
        if (routineToUpdate == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No routine found with id " + routine.getId());

        return routineRepository.save(routine);
    }
}
