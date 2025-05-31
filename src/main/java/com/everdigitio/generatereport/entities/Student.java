package com.everdigitio.generatereport.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rollNumber;
    private String name;
    private String year;
    private String advisor;
    private String gradingPeriod;

    // one to many  relationship mapped by the student entity

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ScoreCard> scoreCards;
}
