package com.ales.fittrackapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor @NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String name;
    private String surname;
    private String username;
    private int age;
    private double weight;
    private int height;
    private String genre;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Routine> routine;
}
