package com.ales.fittrackmobile.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Routine implements Serializable {
    private Long id;
    private String name;
    private String description;
    private List<Exercise> exerciseList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }
}
