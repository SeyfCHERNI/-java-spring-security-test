package com.example.demo.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.demo.entities.Student;
import com.example.demo.repositories.StudentRepository;



@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    public void testGetStudentsByFilters() {
        // create mock data
        List<Student> students = new ArrayList<>();
        students.add(new Student(1L, "John", "Doe", new ClasseSchool(1L, "Math", new Teacher(1L, "Jane", "Smith"))));
        students.add(new Student(2L, "Jane", "Doe", new ClasseSchool(1L, "Math", new Teacher(1L, "Jane", "Smith"))));
        students.add(new Student(3L, "Bob", "Johnson", new ClasseSchool(2L, "English", new Teacher(2L, "Bob", "Johnson"))));

        Pageable pageable = PageRequest.of(0, 10);

        // mock repository method
        when(studentRepository.findByClasseSchoolName("Math", pageable)).thenReturn(new PageImpl<>(students.subList(0, 2), pageable, 2));
        when(studentRepository.findByClasseSchoolTeacherFullName("Jane Smith", pageable)).thenReturn(new PageImpl<>(students.subList(0, 2), pageable, 2));
        when(studentRepository.findByClasseSchoolNameAndClasseSchoolTeacherFullName("Math", "Jane Smith", pageable)).thenReturn(new PageImpl<>(students.subList(0, 2), pageable, 2));
        when(studentRepository.findAll(pageable)).thenReturn(new PageImpl<>(students, pageable, students.size()));

        // test with class name filter
        Page<Student> result1 = studentService.getStudentsByFilters("Math", null, 0, 10);
        assertEquals(2, result1.getTotalElements());

        // test with teacher full name filter
        Page<Student> result2 = studentService.getStudentsByFilters(null, "Jane Smith", 0, 10);
        assertEquals(2, result2.getTotalElements());

        // test with both class name and teacher full name filters
        Page<Student> result3 = studentService.getStudentsByFilters("Math", "Jane Smith", 0, 10);
        assertEquals(2, result3.getTotalElements());

        // test without any filters
        Page<Student> result4 = studentService.getStudentsByFilters(null, null, 0, 10);
        assertEquals(3, result4.getTotalElements());
    }
}
