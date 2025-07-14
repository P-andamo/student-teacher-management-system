package com.bytecraft.studentteachermanagement.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    private String id;

    @NotBlank(message = "Teacher name cannot be blank")
    @Column(nullable = false)
    private String name;

    @Min(value = 1, message = "Age must be positive")
    @Column(nullable = false)
    private int age;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "teacher_courses",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @JsonManagedReference("teacher-courses")
    private Set<Course> courses = new HashSet<>();

    public Teacher() {
        this.id = UUID.randomUUID().toString();
    }

    public Teacher(String name, int age) {
        this();
        this.name = name;
        this.age = age;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public Set<Course> getCourses() { return courses; }
    public void setCourses(Set<Course> courses) { this.courses = courses; }
}
