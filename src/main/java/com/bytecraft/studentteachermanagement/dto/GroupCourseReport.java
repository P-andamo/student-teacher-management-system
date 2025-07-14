package com.bytecraft.studentteachermanagement.dto;

import com.bytecraft.studentteachermanagement.model.Student;
import com.bytecraft.studentteachermanagement.model.Teacher;

import java.util.List;

public class GroupCourseReport {
    private List<Teacher> teachers;
    private List<Student> students;

    public GroupCourseReport(List<Teacher> teachers, List<Student> students) {
        this.teachers = teachers;
        this.students = students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }
    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Student> getStudents() {
        return students;
    }
    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
