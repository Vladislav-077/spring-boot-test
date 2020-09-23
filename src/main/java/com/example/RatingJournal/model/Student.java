package com.example.RatingJournal.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "learner")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //id в базе.

    @Column(name = "name")
    private String name; // column name by table learner

    @Column(name = "class_number")
    private int classNumber; // column class_number by table learner

    @Column(name = "predmet")
    private String predmet; // column predmet by table learner

    @Column(name = "rating")
    private int rating; // column rating by table learner

}

