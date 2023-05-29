package com.ales.fittrackmobile.entities;

import java.util.List;

public class Exercise {
    private Long id;
    private String name;
    private String description;
    private String type;
    private List<Muscles> muscles;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Muscles> getMuscles() {
        return muscles;
    }

    public void setMuscles(List<Muscles> muscles) {
        this.muscles = muscles;
    }
}
