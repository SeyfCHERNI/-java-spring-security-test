package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.Student;
import com.example.demo.services.StudentService;

@RestController
@RequestMapping("api/students")
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<Page<Student>> getStudentsByFilters(
            @RequestParam(required = false) String ClasseSchoolName,
            @RequestParam(required = false) String teacherFullName,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        
        Page<Student> students = studentService.getStudentsByFilters(ClasseSchoolName, teacherFullName, pageNumber, pageSize);
        return ResponseEntity.ok(students);
    }
}
