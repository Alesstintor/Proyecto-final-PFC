package com.ales.fittrack.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Routine {

    @Id
    private int id;
    private String name;
    private String description;
    @ManyToMany
    private List<Exercise> exerciseList;
}
