package com.ales.fittrack.model;

import lombok.Data;

import java.util.List;

@Data
public class User {

    private int id;
    private String name;
    private String surname;
    private int age;
    private double weight;
    private int height;
    private String genre;
    private List<Routine> routineList;

}
