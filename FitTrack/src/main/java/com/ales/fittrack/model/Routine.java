package com.ales.fittrack.model;

import lombok.Data;

import java.util.List;

@Data
public class Routine {

    private int id;
    private String name;
    private String description;
    private List<Exercise> routineExerciseList;
}
