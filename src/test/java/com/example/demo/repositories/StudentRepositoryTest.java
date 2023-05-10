package com.example.demo.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;

import com.example.demo.entities.ClasseSchool;
import com.example.demo.entities.Student;
import com.example.demo.entities.Teacher;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void whenFindByClasseSchoolName_thenReturnStudentList() {
        // given
        ClasseSchool classeSchool = new ClasseSchool(1L, "Math", new Teacher(1L, "Jane", "Smith"));
        entityManager.persist(classeSchool);

        Student student1 = new Student(1L, "John", "Doe", classeSchool);
        entityManager.persist(student1);

        Student student2 = new Student(2L, "Jane", "Doe", classeSchool);
        entityManager.persist(student2);

        entityManager.flush();

        // when
        Page<Student> found = studentRepository.findByClasseSchoolName(classeSchool.getName(), PageRequest.of(0, 10));

        // then
        assertThat(found.getContent()).isEqualTo(Arrays.asList(student1, student2));
    }

    @Test
    public void whenFindByClasseSchoolTeacherFullName_thenReturnStudentList() {
        // given
        ClasseSchool classeSchool = new ClasseSchool(1L, "Math", new Teacher(1L, "Jane", "Smith"));
        entityManager.persist(classeSchool);

        Student student1 = new Student(1L, "John", "Doe", classeSchool);
        entityManager.persist(student1);

        ClasseSchool otherClasseSchool = new ClasseSchool(2L, "Physics", new Teacher(2L, "John", "Doe"));
        entityManager.persist(otherClasseSchool);

        Student student2 = new Student(2L, "Jane", "Doe", otherClasseSchool);
        entityManager.persist(student2);

        entityManager.flush();

        // when
        Page<Student> found = studentRepository.findByClasseSchoolTeacherFullName(classeSchool.getTeacher().getFirstName(), PageRequest.of(0, 10));

        // then
        assertThat(found.getContent()).isEqualTo(Arrays.asList(student1));
    }

    @Test
    public void whenFindByClasseSchoolNameAndClasseSchoolTeacherFullName_thenReturnStudentList() {
        // given
        ClasseSchool classeSchool = new ClasseSchool(1L, "Math", new Teacher(1L, "Jane", "Smith"));
        entityManager.persist(classeSchool);

        Student student1 = new Student(1L, "John", "Doe", classeSchool);
        entityManager.persist(student1);

        ClasseSchool otherClasseSchool = new ClasseSchool(2L, "Physics", new Teacher(2L, "John", "Doe"));
        entityManager.persist(otherClasseSchool);

        Student student2 = new Student(2L, "Jane", "Doe", otherClasseSchool);
        entityManager.persist(student2);

        entityManager.flush();

        // when
        Page<Student> found = studentRepository.findByClasseSchoolNameAndClasseSchoolTeacherFullName(classeSchool.getName(),
                classeSchool.getTeacher().getFirstName(), PageRequest.of(0, 10));

        // then
        assertThat(found.getContent()).isEqualTo(Arrays.asList(student1));
    }
}
