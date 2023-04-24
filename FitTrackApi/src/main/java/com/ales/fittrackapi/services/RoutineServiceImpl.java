package com.ales.fittrackapi.services;

import com.ales.fittrackapi.entities.Routine;
import com.ales.fittrackapi.repositories.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public String deleteByExample(Routine routine) {
        return null;
    }

    @Override
    public String update(Routine routine) {
        return null;
    }
}
