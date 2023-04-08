package com.ales.fittrack.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    private String name;
    private String surname;
    private int age;
    private double weight;
    private int height;
    private String genre;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Routine> routine;
}
