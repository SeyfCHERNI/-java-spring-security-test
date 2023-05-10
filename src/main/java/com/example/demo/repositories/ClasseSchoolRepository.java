package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.ClasseSchool;


@Repository
public interface ClasseSchoolRepository extends JpaRepository<ClasseSchool, Long> {
    List<ClasseSchool> findAllByOrderByIdAsc();
}