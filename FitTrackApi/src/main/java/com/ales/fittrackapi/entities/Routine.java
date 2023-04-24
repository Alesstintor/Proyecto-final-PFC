package com.ales.fittrackapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Routine {

    @Id
    private int id;
    private String name;
    private String description;
    @ManyToMany
    private List<Exercise> exerciseList;
}
