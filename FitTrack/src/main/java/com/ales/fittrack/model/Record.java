package com.ales.fittrack.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Record {

    private int id;
    private User user;
    private Routine routine;
    private Date date;
    private List<Exercise> recordExerciseList;

}
