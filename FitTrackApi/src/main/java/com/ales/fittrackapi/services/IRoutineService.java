package com.ales.fittrackapi.services;

import com.ales.fittrackapi.entities.Routine;

import java.util.List;

public interface IRoutineService {

    List<Routine> findAll();
    Routine findById(Long id);
    List<Routine> findAllByExample(Routine routine);
    Routine save(Routine routine);
    List<Routine> saveAll(List<Routine> routines);
    String deleteById(Long id);
    String deleteByExample(Routine routine);
    Routine update(Routine routine);
}
