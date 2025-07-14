package com.bytecraft.studentteachermanagement.controller;

import com.bytecraft.studentteachermanagement.dto.CoursesByTypeReport;
import com.bytecraft.studentteachermanagement.dto.GroupCourseReport;
import com.bytecraft.studentteachermanagement.model.Course;
import com.bytecraft.studentteachermanagement.model.CourseType;
import com.bytecraft.studentteachermanagement.model.Student;
import com.bytecraft.studentteachermanagement.model.Teacher;
import com.bytecraft.studentteachermanagement.service.StudentTeacherManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentTeacherController {

    @Autowired
    private StudentTeacherManagementService managementService;

    // Course endpoints
    @PostMapping("/courses")
    public ResponseEntity<Course> addCourse(@RequestParam String name, @RequestParam CourseType courseType) {
        Course course = managementService.addCourse(name, courseType);
        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<Boolean> removeCourse(@PathVariable String courseId) {
        boolean removed = managementService.removeCourse(courseId);
        return ResponseEntity.ok(removed);
    }

    @PutMapping("/courses/{courseId}")
    public ResponseEntity<Boolean> modifyCourse(@PathVariable String courseId,
                                                @RequestParam(required = false) String name,
                                                @RequestParam(required = false) CourseType courseType) {
        boolean modified = managementService.modifyCourse(courseId, name, courseType);
        return ResponseEntity.ok(modified);
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(managementService.getAllCourses());
    }

    @GetMapping("/courses/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable String courseId) {
        Optional<Course> course = managementService.getCourseById(courseId);
        return course.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Student endpoints
    @PostMapping("/students")
    public ResponseEntity<Student> addStudent(@RequestParam String name,
                                              @RequestParam int age,
                                              @RequestParam String groupName) {
        Student student = managementService.addStudent(name, age, groupName);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<Boolean> removeStudent(@PathVariable String studentId) {
        boolean removed = managementService.removeStudent(studentId);
        return ResponseEntity.ok(removed);
    }

    @PutMapping("/students/{studentId}")
    public ResponseEntity<Boolean> modifyStudent(@PathVariable String studentId,
                                                 @RequestParam(required = false) String name,
                                                 @RequestParam(required = false) Integer age,
                                                 @RequestParam(required = false) String groupName) {
        boolean modified = managementService.modifyStudent(studentId, name, age, groupName);
        return ResponseEntity.ok(modified);
    }

    @PostMapping("/students/{studentId}/enroll/{courseId}")
    public ResponseEntity<Boolean> enrollStudentInCourse(@PathVariable String studentId,
                                                         @PathVariable String courseId) {
        boolean enrolled = managementService.enrollStudentInCourse(studentId, courseId);
        return ResponseEntity.ok(enrolled);
    }

    @DeleteMapping("/students/{studentId}/enroll/{courseId}")
    public ResponseEntity<Boolean> unenrollStudentFromCourse(@PathVariable String studentId,
                                                             @PathVariable String courseId) {
        boolean unenrolled = managementService.unenrollStudentFromCourse(studentId, courseId);
        return ResponseEntity.ok(unenrolled);
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(managementService.getAllStudents());
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable String studentId) {
        Optional<Student> student = managementService.getStudentById(studentId);
        return student.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Teacher endpoints
    @PostMapping("/teachers")
    public ResponseEntity<Teacher> addTeacher(@RequestParam String name, @RequestParam int age) {
        Teacher teacher = managementService.addTeacher(name, age);
        return ResponseEntity.ok(teacher);
    }

    @DeleteMapping("/teachers/{teacherId}")
    public ResponseEntity<Boolean> removeTeacher(@PathVariable String teacherId) {
        boolean removed = managementService.removeTeacher(teacherId);
        return ResponseEntity.ok(removed);
    }

    @PutMapping("/teachers/{teacherId}")
    public ResponseEntity<Boolean> modifyTeacher(@PathVariable String teacherId,
                                                 @RequestParam(required = false) String name,
                                                 @RequestParam(required = false) Integer age) {
        boolean modified = managementService.modifyTeacher(teacherId, name, age);
        return ResponseEntity.ok(modified);
    }

    @PostMapping("/teachers/{teacherId}/assign/{courseId}")
    public ResponseEntity<Boolean> assignTeacherToCourse(@PathVariable String teacherId,
                                                         @PathVariable String courseId) {
        boolean assigned = managementService.assignTeacherToCourse(teacherId, courseId);
        return ResponseEntity.ok(assigned);
    }

    @DeleteMapping("/teachers/{teacherId}/assign/{courseId}")
    public ResponseEntity<Boolean> unassignTeacherFromCourse(@PathVariable String teacherId,
                                                             @PathVariable String courseId) {
        boolean unassigned = managementService.unassignTeacherFromCourse(teacherId, courseId);
        return ResponseEntity.ok(unassigned);
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return ResponseEntity.ok(managementService.getAllTeachers());
    }

    @GetMapping("/teachers/{teacherId}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable String teacherId) {
        Optional<Teacher> teacher = managementService.getTeacherById(teacherId);
        return teacher.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Report endpoints
    @GetMapping("/reports/student-count")
    public ResponseEntity<Long> getStudentCount() {
        return ResponseEntity.ok(managementService.getStudentCount());
    }

    @GetMapping("/reports/teacher-count")
    public ResponseEntity<Long> getTeacherCount() {
        return ResponseEntity.ok(managementService.getTeacherCount());
    }

    @GetMapping("/reports/courses-by-type")
    public ResponseEntity<CoursesByTypeReport> getCoursesByType() {
        return ResponseEntity.ok(managementService.getCoursesByType());
    }

    @GetMapping("/reports/students-in-course/{courseId}")
    public ResponseEntity<List<Student>> getStudentsInCourse(@PathVariable String courseId) {
        return ResponseEntity.ok(managementService.getStudentsInCourse(courseId));
    }

    @GetMapping("/reports/students-in-group/{groupName}")
    public ResponseEntity<List<Student>> getStudentsInGroup(@PathVariable String groupName) {
        return ResponseEntity.ok(managementService.getStudentsInGroup(groupName));
    }

    @GetMapping("/reports/group-course/{groupName}/{courseId}")
    public ResponseEntity<GroupCourseReport> getTeachersAndStudentsForGroupAndCourse(
            @PathVariable String groupName, @PathVariable String courseId) {
        return ResponseEntity.ok(managementService.getTeachersAndStudentsForGroupAndCourse(groupName, courseId));
    }

    @GetMapping("/reports/students-older-than/{minAge}/in-course/{courseId}")
    public ResponseEntity<List<Student>> getStudentsOlderThanAgeInCourse(@PathVariable int minAge,
                                                                         @PathVariable String courseId) {
        return ResponseEntity.ok(managementService.getStudentsOlderThanAgeInCourse(minAge, courseId));
    }
}
