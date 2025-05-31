package com.everdigitio.generatereport.controller;
import com.everdigitio.generatereport.entities.Student;
import com.everdigitio.generatereport.services.StudentService;
import com.everdigitio.generatereport.utils.ReportUtil;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private final StudentService studentService;


    @GetMapping("/student/{rollNumber}")
    public ResponseEntity<byte[]> generateStudentReport(@PathVariable(name = "rollNumber") Integer rollNumber) throws IOException {
        try {
            // Fetch student with given roll number
            List<Student> students = null;
            if (rollNumber != null) {
                students = List.of(studentService.getStudentById(rollNumber));
            } else {
                students.add(new Student());
            }

            // Path to the compiled .jasper file
            String jasperPath = new File("src/main/resources/reports/StudentScoreCardFromDB.jasper").getAbsolutePath();

            // Parameters for the report
            HashMap<String, Object> parameters = new HashMap<>();
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(students.get(0).getScoreCards());
            parameters.put("TABLE_DATA_SOURCE", dataSource);

            // Generate the PDF report
            byte[] pdfData = ReportUtil.generateReport(students, jasperPath, parameters);

            // Return the PDF as a response
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=student_report.pdf")
                    .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
                    .body(pdfData);

        } catch (JRException e) {
            throw new RuntimeException("Error generating report", e);
        }
    }
}