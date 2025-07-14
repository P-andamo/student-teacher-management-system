package com.bytecraft.studentteachermanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    private String id;

    @NotBlank(message = "Course name cannot be blank")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Course type cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourseType courseType;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    @JsonBackReference("student-courses")
    private Set<Student> students = new HashSet<>();

    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    @JsonBackReference("teacher-courses")
    private Set<Teacher> teachers = new HashSet<>();

    public Course() {
        this.id = UUID.randomUUID().toString();
    }

    public Course(String name, CourseType courseType) {
        this();
        this.name = name;
        this.courseType = courseType;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public CourseType getCourseType() { return courseType; }
    public void setCourseType(CourseType courseType) { this.courseType = courseType; }

    public Set<Student> getStudents() { return students; }
    public void setStudents(Set<Student> students) { this.students = students; }

    public Set<Teacher> getTeachers() { return teachers; }
    public void setTeachers(Set<Teacher> teachers) { this.teachers = teachers; }
}
