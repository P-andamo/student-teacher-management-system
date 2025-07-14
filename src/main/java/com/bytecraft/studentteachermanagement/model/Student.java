package com.bytecraft.studentteachermanagement.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "students")
public class Student {
    @Id
    private String id;

    @NotBlank(message = "Student name cannot be blank")
    @Column(nullable = false)
    private String name;

    @Min(value = 1, message = "Age must be positive")
    @Column(nullable = false)
    private int age;

    @NotBlank(message = "Group cannot be blank")
    @Column(nullable = false)
    private String groupName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @JsonManagedReference("student-courses")
    private Set<Course> courses = new HashSet<>();

    public Student() {
        this.id = UUID.randomUUID().toString();
    }

    public Student(String name, int age, String groupName) {
        this();
        this.name = name;
        this.age = age;
        this.groupName = groupName;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGroupName() { return groupName; }
    public void setGroupName(String groupName) { this.groupName = groupName; }

    public Set<Course> getCourses() { return courses; }
    public void setCourses(Set<Course> courses) { this.courses = courses; }
}
