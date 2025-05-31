package com.everdigitio.generatereport.services;

import com.everdigitio.generatereport.entities.Student;
import com.everdigitio.generatereport.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public Student getStudentById(Integer rollNumber) {
        return studentRepository.findById(rollNumber).orElseThrow(() -> new RuntimeException("Student not Found"+rollNumber));
    }
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
