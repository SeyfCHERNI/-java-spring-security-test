package com.example.demo.services;

import org.springframework.data.domain.Page;

import org.springframework.stereotype.Service;

import com.example.demo.entities.Student;

@Service
public interface StudentService {

    Page<Student> getStudentsByFilters(String classeSchoolName, String teacherFullName, int pageNumber, int pageSize);

	Object getAllStudents();

	Object getStudentById(long l);



}