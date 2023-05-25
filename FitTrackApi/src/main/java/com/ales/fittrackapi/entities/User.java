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
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Roles> type;
    private String name;
    private String surname;
    @Column(unique = true)
    private String username;
    private String password;
    private int age;
    private double weight;
    private int height;
    private String genre;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Routine> routine;
}
