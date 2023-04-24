package com.ales.fittrackapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Record {

    @Id
    private int id;
    private Date date;
    @ManyToOne
    private User user;
    @ManyToOne
    private Routine routine;
    @ManyToMany
    private List<Exercise> exerciseList;

}
