package com.example.demo.repositories;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Page<Student> findByClasseSchoolName(String classeSchoolName, Pageable pageable);

    Page<Student> findByClasseSchoolTeacherFullName(String teacherFullName, Pageable pageable);

    Page<Student> findByClasseSchoolNameAndClasseSchoolTeacherFullName(String classeSchoolName, String teacherFullName, Pageable pageable);

}