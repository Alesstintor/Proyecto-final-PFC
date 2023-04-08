package com.ales.fittrack.model;

import lombok.Data;

import java.util.Set;

@Data
public class Exercise {

    private int id;
    private String name;
    private String description;
    private String type;
    private Set<String> muscles;
}
