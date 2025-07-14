package com.bytecraft.studentteachermanagement.service;

import com.bytecraft.studentteachermanagement.dto.CoursesByTypeReport;
import com.bytecraft.studentteachermanagement.dto.GroupCourseReport;
import com.bytecraft.studentteachermanagement.model.Course;
import com.bytecraft.studentteachermanagement.model.CourseType;
import com.bytecraft.studentteachermanagement.model.Student;
import com.bytecraft.studentteachermanagement.model.Teacher;
import com.bytecraft.studentteachermanagement.repository.CourseRepository;
import com.bytecraft.studentteachermanagement.repository.StudentRepository;
import com.bytecraft.studentteachermanagement.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@Transactional
public class StudentTeacherManagementService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    // Course management
    public Course addCourse(String name, CourseType courseType) {
        Course course = new Course(name, courseType);
        return courseRepository.save(course);
    }

    public boolean removeCourse(String courseId) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();

            // Remove course from all students and teachers
            for (Student student : course.getStudents()) {
                student.getCourses().remove(course);
            }
            for (Teacher teacher : course.getTeachers()) {
                teacher.getCourses().remove(course);
            }

            courseRepository.delete(course);
            return true;
        }
        return false;
    }

    public boolean modifyCourse(String courseId, String name, CourseType courseType) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();
            if (name != null) course.setName(name);
            if (courseType != null) course.setCourseType(courseType);
            courseRepository.save(course);
            return true;
        }
        return false;
    }

    // Student management
    public Student addStudent(String name, int age, String groupName) {
        Student student = new Student(name, age, groupName);
        return studentRepository.save(student);
    }

    public boolean removeStudent(String studentId) {
        if (studentRepository.existsById(studentId)) {
            studentRepository.deleteById(studentId);
            return true;
        }
        return false;
    }

    public boolean modifyStudent(String studentId, String name, Integer age, String groupName) {
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            if (name != null) student.setName(name);
            if (age != null) student.setAge(age);
            if (groupName != null) student.setGroupName(groupName);
            studentRepository.save(student);
            return true;
        }

        return false;
    }

    public boolean enrollStudentInCourse(String studentId, String courseId) {
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        Optional<Course> courseOpt = courseRepository.findById(courseId);

        if (studentOpt.isPresent() && courseOpt.isPresent()) {
            Student student = studentOpt.get();
            Course course = courseOpt.get();
            student.getCourses().add(course);
            studentRepository.save(student);
            return true;
        }
        return false;
    }

    public boolean unenrollStudentFromCourse(String studentId, String courseId) {
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        Optional<Course> courseOpt = courseRepository.findById(courseId);

        if (studentOpt.isPresent() && courseOpt.isPresent()) {
            Student student = studentOpt.get();
            Course course = courseOpt.get();
            student.getCourses().remove(course);
            studentRepository.save(student);
            return true;
        }
        return false;
    }

    // Teacher management
    public Teacher addTeacher(String name, int age) {
        Teacher teacher = new Teacher(name, age);
        return teacherRepository.save(teacher);
    }

    public boolean removeTeacher(String teacherId) {
        if (teacherRepository.existsById(teacherId)) {
            teacherRepository.deleteById(teacherId);
            return true;
        }
        return false;
    }

    public boolean modifyTeacher(String teacherId, String name, Integer age) {
        Optional<Teacher> teacherOpt = teacherRepository.findById(teacherId);
        if (teacherOpt.isPresent()) {
            Teacher teacher = teacherOpt.get();
            if (name != null) teacher.setName(name);
            if (age != null) teacher.setAge(age);
            teacherRepository.save(teacher);
            return true;
        }
        return false;
    }

    public boolean assignTeacherToCourse(String teacherId, String courseId) {

        Optional<Teacher> teacherOpt = teacherRepository.findById(teacherId);
        Optional<Course> courseOpt = courseRepository.findById(courseId);

        if (teacherOpt.isPresent() && courseOpt.isPresent()) {
            Teacher teacher = teacherOpt.get();
            Course course = courseOpt.get();
            teacher.getCourses().add(course);
            teacherRepository.save(teacher);
            return true;
        }

        return false;
    }

    public boolean unassignTeacherFromCourse(String teacherId, String courseId) {
        Optional<Teacher> teacherOpt = teacherRepository.findById(teacherId);
        Optional<Course> courseOpt = courseRepository.findById(courseId);

        if (teacherOpt.isPresent() && courseOpt.isPresent()) {
            Teacher teacher = teacherOpt.get();
            Course course = courseOpt.get();
            teacher.getCourses().remove(course);
            teacherRepository.save(teacher);
            return true;
        }
        return false;
    }

    // Reporting methods
    @Transactional(readOnly = true)
    public long getStudentCount() {
        return studentRepository.count();
    }

    @Transactional(readOnly = true)
    public long getTeacherCount() {
        return teacherRepository.count();
    }

    @Transactional(readOnly = true)
    public CoursesByTypeReport getCoursesByType() {
        Map<String, Long> counts = new HashMap<>();
        for (CourseType type : CourseType.values()) {
            counts.put(type.getDisplayName(), courseRepository.countByCourseType(type));
        }
        return new CoursesByTypeReport(counts);
    }

    @Transactional(readOnly = true)
    public List<Student> getStudentsInCourse(String courseId) {
        return studentRepository.findStudentsInCourse(courseId);
    }

    @Transactional(readOnly = true)
    public List<Student> getStudentsInGroup(String groupName) {
        return studentRepository.findByGroupName(groupName);
    }

    @Transactional(readOnly = true)
    public GroupCourseReport getTeachersAndStudentsForGroupAndCourse(String groupName, String courseId) {
        List<Teacher> teachers = teacherRepository.findTeachersInCourse(courseId);
        List<Student> students = studentRepository.findStudentsInGroupAndCourse(groupName, courseId);
        return new GroupCourseReport(teachers, students);
    }

    @Transactional(readOnly = true)
    public List<Student> getStudentsOlderThanAgeInCourse(int minAge, String courseId) {
        return studentRepository.findStudentsOlderThanAgeInCourse(minAge, courseId);
    }

    // Additional API methods
    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Student> getStudentById(String studentId) {
        return studentRepository.findById(studentId);
    }

    @Transactional(readOnly = true)
    public Optional<Teacher> getTeacherById(String teacherId) {
        return teacherRepository.findById(teacherId);
    }

    @Transactional(readOnly = true)
    public Optional<Course> getCourseById(String courseId) {
        return courseRepository.findById(courseId);
    }
}
