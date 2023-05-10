package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Student;
import com.example.demo.repositories.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Page<Student> getStudentsByFilters(String classeSchoolName, String teacherFullName, int pageNumber, int pageSize) {
        // Create a Pageable object to retrieve the results paginated
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        // Check if both filters are present
        if (classeSchoolName != null && teacherFullName != null) {
            return studentRepository.findByClasseSchoolNameAndClasseSchoolTeacherFullName(classeSchoolName, teacherFullName, pageable);
        }

        // Check if only class name filter is present
        if (classeSchoolName != null) {
            return studentRepository.findByClasseSchoolName(classeSchoolName, pageable);
        }

        // Check if only teacher full name filter is present
        if (teacherFullName != null) {
            return studentRepository.findByClasseSchoolTeacherFullName(teacherFullName, pageable);
        }

        // If no filters are present, return all students paginated
        return studentRepository.findAll(pageable);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            return optionalStudent.get();
        } else {
            return null; // or you can return a default value, such as a new Student object with default values
        }
    }
}
