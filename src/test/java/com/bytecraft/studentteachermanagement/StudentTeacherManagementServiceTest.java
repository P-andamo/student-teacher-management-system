package com.bytecraft.studentteachermanagement;

import com.bytecraft.studentteachermanagement.dto.CoursesByTypeReport;
import com.bytecraft.studentteachermanagement.dto.GroupCourseReport;
import com.bytecraft.studentteachermanagement.model.Course;
import com.bytecraft.studentteachermanagement.model.CourseType;
import com.bytecraft.studentteachermanagement.model.Student;
import com.bytecraft.studentteachermanagement.model.Teacher;
import com.bytecraft.studentteachermanagement.service.StudentTeacherManagementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
@Transactional
public class StudentTeacherManagementServiceTest {

    @Autowired
    private StudentTeacherManagementService service;

    @Test
    public void testAddCourse() {
        Course course = service.addCourse("Mathematics", CourseType.MAIN);

        assertNotNull(course);
        assertNotNull(course.getId());
        assertEquals("Mathematics", course.getName());
        assertEquals(CourseType.MAIN, course.getCourseType());
    }

    @Test
    public void testAddStudent() {
        Student student = service.addStudent("Stefan Hristov", 20, "Group A");

        assertNotNull(student);
        assertNotNull(student.getId());
        assertEquals("Stefan Hristov", student.getName());
        assertEquals(20, student.getAge());
        assertEquals("Group A", student.getGroupName());
    }

    @Test
    public void testAddTeacher() {
        Teacher teacher = service.addTeacher("Maria Ilieva", 35);

        assertNotNull(teacher);
        assertNotNull(teacher.getId());
        assertEquals("Maria Ilieva", teacher.getName());
        assertEquals(35, teacher.getAge());
    }

    @Test
    public void testModifyStudent() {
        Student student = service.addStudent("Ivan Ivanov", 20, "Group A");
        boolean result = service.modifyStudent(student.getId(), "Ivan Updated", 21, "Group B");

        assertTrue(result);

        Optional<Student> updatedStudent = service.getStudentById(student.getId());
        assertTrue(updatedStudent.isPresent());
        assertEquals("Ivan Updated", updatedStudent.get().getName());
        assertEquals(21, updatedStudent.get().getAge());
        assertEquals("Group B", updatedStudent.get().getGroupName());
    }

    @Test
    public void testModifyTeacher() {
        Teacher teacher = service.addTeacher("Maria Georgieva", 35);
        boolean result = service.modifyTeacher(teacher.getId(), "Maria Updated", 36);

        assertTrue(result);

        Optional<Teacher> updatedTeacher = service.getTeacherById(teacher.getId());
        assertTrue(updatedTeacher.isPresent());
        assertEquals("Maria Updated", updatedTeacher.get().getName());
        assertEquals(36, updatedTeacher.get().getAge());
    }

    @Test
    public void testRemoveStudent() {
        Student student = service.addStudent("Valeri Valeriev", 20, "Group A");
        boolean result = service.removeStudent(student.getId());

        assertTrue(result);

        Optional<Student> removedStudent = service.getStudentById(student.getId());
        assertFalse(removedStudent.isPresent());
    }

    @Test
    public void testRemoveTeacher() {
        Teacher teacher = service.addTeacher("Jane Smith", 35);
        boolean result = service.removeTeacher(teacher.getId());

        assertTrue(result);

        Optional<Teacher> removedTeacher = service.getTeacherById(teacher.getId());
        assertFalse(removedTeacher.isPresent());
    }

    @Test
    public void testEnrollStudentInCourse() {
        Student student = service.addStudent("John Doe", 20, "Group A");
        Course course = service.addCourse("Mathematics", CourseType.MAIN);

        boolean result = service.enrollStudentInCourse(student.getId(), course.getId());
        assertTrue(result);

        Optional<Student> updatedStudent = service.getStudentById(student.getId());
        assertTrue(updatedStudent.isPresent());
        assertTrue(updatedStudent.get().getCourses().stream()
                .anyMatch(c -> c.getId().equals(course.getId())));
    }

    @Test
    public void testAssignTeacherToCourse() {
        Teacher teacher = service.addTeacher("Jane Smith", 35);
        Course course = service.addCourse("Mathematics", CourseType.MAIN);

        boolean result = service.assignTeacherToCourse(teacher.getId(), course.getId());
        assertTrue(result);

        Optional<Teacher> updatedTeacher = service.getTeacherById(teacher.getId());
        assertTrue(updatedTeacher.isPresent());
        assertTrue(updatedTeacher.get().getCourses().stream()
                .anyMatch(c -> c.getId().equals(course.getId())));
    }

    @Test
    public void testGetStudentCount() {
        assertEquals(5, service.getStudentCount());

        service.addStudent("Tania Vladimirova", 20, "Group A");
        service.addStudent("Jana Aneva", 22, "Group B");

        assertEquals(7, service.getStudentCount());
    }

    @Test
    public void testGetTeacherCount() {
        assertEquals(3, service.getTeacherCount());

        service.addTeacher("Prof. Stoyanov", 45);
        service.addTeacher("Prof. Maleev", 50);

        assertEquals(5, service.getTeacherCount());
    }

    @Test
    public void testGetCoursesByType() {
        service.addCourse("Mathematics", CourseType.MAIN);
        service.addCourse("Physics", CourseType.MAIN);
        service.addCourse("Art", CourseType.SECONDARY);

        CoursesByTypeReport report = service.getCoursesByType();

        assertEquals(4L, report.getCoursesByType().get("Main"));
        assertEquals(3L, report.getCoursesByType().get("Secondary"));
    }

    @Test
    public void testGetStudentsInCourse() {
        Student student1 = service.addStudent("Joan Stanislavov", 20, "Group A");
        Student student2 = service.addStudent("Vasil Petrov", 22, "Group B");
        Course course = service.addCourse("Mathematics", CourseType.MAIN);

        service.enrollStudentInCourse(student1.getId(), course.getId());
        service.enrollStudentInCourse(student2.getId(), course.getId());

        List<Student> studentsInCourse = service.getStudentsInCourse(course.getId());
        assertEquals(2, studentsInCourse.size());
    }

    @Test
    public void testGetStudentsInGroup() {
        service.addStudent("Bobi Vasilev", 20, "Group A");
        service.addStudent("Jeni Petrova", 22, "Group A");
        service.addStudent("Bogdan Bogdanov", 21, "Group B");

        List<Student> studentsInGroupA = service.getStudentsInGroup("Group A");
        List<Student> studentsInGroupB = service.getStudentsInGroup("Group B");

        assertEquals(5, studentsInGroupA.size());
        assertEquals(3, studentsInGroupB.size());
    }

    @Test
    public void testGetTeachersAndStudentsForGroupAndCourse() {
        Teacher teacher = service.addTeacher("Prof. Ivanov", 45);
        Student student1 = service.addStudent("Georgi Petrov", 20, "Group A");
        Student student2 = service.addStudent("Maria Petrova", 22, "Group A");
        Student student3 = service.addStudent("Ivan Ivanov", 21, "Group B");
        Course course = service.addCourse("Mathematics", CourseType.MAIN);

        service.assignTeacherToCourse(teacher.getId(), course.getId());
        service.enrollStudentInCourse(student1.getId(), course.getId());
        service.enrollStudentInCourse(student2.getId(), course.getId());
        service.enrollStudentInCourse(student3.getId(), course.getId());

        GroupCourseReport report = service.getTeachersAndStudentsForGroupAndCourse("Group A", course.getId());

        assertEquals(1, report.getTeachers().size());
        assertEquals(2, report.getStudents().size());
    }

    @Test
    public void testGetStudentsOlderThanAgeInCourse() {
        Student student1 = service.addStudent("Georgi Petrov", 18, "Group A");
        Student student2 = service.addStudent("Georgi Petrov", 22, "Group A");
        Student student3 = service.addStudent("Ivan Ivanov", 25, "Group B");
        Course course = service.addCourse("Mathematics", CourseType.MAIN);

        service.enrollStudentInCourse(student1.getId(), course.getId());
        service.enrollStudentInCourse(student2.getId(), course.getId());
        service.enrollStudentInCourse(student3.getId(), course.getId());

        List<Student> studentsOlderThan20 = service.getStudentsOlderThanAgeInCourse(20, course.getId());

        assertEquals(2, studentsOlderThan20.size());
        assertTrue(studentsOlderThan20.stream().allMatch(s -> s.getAge() > 20));
    }

    @Test
    public void testUnenrollStudentFromCourse() {
        Student student = service.addStudent("Georgi Petrov", 20, "Group A");
        Course course = service.addCourse("Mathematics", CourseType.MAIN);

        service.enrollStudentInCourse(student.getId(), course.getId());
        boolean result = service.unenrollStudentFromCourse(student.getId(), course.getId());

        assertTrue(result);

        Optional<Student> updatedStudent = service.getStudentById(student.getId());
        assertTrue(updatedStudent.isPresent());
        assertFalse(updatedStudent.get().getCourses().stream()
                .anyMatch(c -> c.getId().equals(course.getId())));
    }

    @Test
    public void testUnassignTeacherFromCourse() {
        Teacher teacher = service.addTeacher("Gergana Petrova", 35);
        Course course = service.addCourse("Mathematics", CourseType.MAIN);

        service.assignTeacherToCourse(teacher.getId(), course.getId());
        boolean result = service.unassignTeacherFromCourse(teacher.getId(), course.getId());

        assertTrue(result);

        Optional<Teacher> updatedTeacher = service.getTeacherById(teacher.getId());
        assertTrue(updatedTeacher.isPresent());
        assertFalse(updatedTeacher.get().getCourses().stream()
                .anyMatch(c -> c.getId().equals(course.getId())));
    }

    @Test
    public void testRemoveCourse() {
        Course course = service.addCourse("Mathematics", CourseType.MAIN);
        Student student = service.addStudent("Blagovest Anev", 20, "Group A");
        Teacher teacher = service.addTeacher("Stanislav Mateev", 35);

        service.enrollStudentInCourse(student.getId(), course.getId());
        service.assignTeacherToCourse(teacher.getId(), course.getId());

        boolean result = service.removeCourse(course.getId());
        assertTrue(result);

        Optional<Course> removedCourse = service.getCourseById(course.getId());
        assertFalse(removedCourse.isPresent());
    }
}
