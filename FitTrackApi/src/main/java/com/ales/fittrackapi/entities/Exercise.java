package com.ales.fittrackapi.entities;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Entity
public class Exercise {

    @Id
    private int id;
    private String name;
    private String description;
    private String type;
    @ElementCollection
    private Set<String> muscles;
    @ManyToMany(mappedBy = "exerciseList")
    private List<Record> recordList;
}
