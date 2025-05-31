package com.everdigitio.generatereport.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String subjectName;
    private double totalMarks;
    private double marksObtained;

    //Many to One relationship with student
    @ManyToOne
    @JoinColumn(name ="roll_number", nullable=false)
    private Student student;
}
