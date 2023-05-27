package com.ales.fittrackapi.services.routine;

import com.ales.fittrackapi.entities.Routine;

import java.util.List;

public interface IRoutineService {

    List<Routine> findAll();
    Routine findById(Long id);
    List<Routine> findAllByExample(Routine routine);
    Routine save(Routine routine);
    List<Routine> saveAll(List<Routine> routines);
    void deleteById(Long id);
    void deleteByExample(Routine routine);
    Routine update(Routine routine);
}
