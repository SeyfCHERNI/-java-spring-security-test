package com.example.demo.entities;


import javax.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClasseSchool classeSchool;

    

    public Student() {}

    public Student(String firstName, String lastName, ClasseSchool classeSchool) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.classeSchool = classeSchool;
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ClasseSchool getClassSchool() {
        return classeSchool;
    }

    public void setClassSchool(ClasseSchool classeSchool) {
        this.classeSchool = classeSchool;
    }
}
