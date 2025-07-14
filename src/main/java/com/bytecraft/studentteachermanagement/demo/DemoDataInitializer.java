package com.bytecraft.studentteachermanagement.demo;

import com.bytecraft.studentteachermanagement.model.CourseType;
import com.bytecraft.studentteachermanagement.service.StudentTeacherManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoDataInitializer implements CommandLineRunner {

    @Autowired
    private StudentTeacherManagementService service;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Student Teacher Management System Demo");
        System.out.println("=" .repeat(50));

        // Add courses
        var mathCourse = service.addCourse("Mathematics", CourseType.MAIN);
        var physicsCourse = service.addCourse("Physics", CourseType.MAIN);
        var artCourse = service.addCourse("Art", CourseType.SECONDARY);
        var musicCourse = service.addCourse("Music", CourseType.SECONDARY);

        System.out.println("Added courses: " + service.getAllCourses().size());

        // Add students
        var student1 = service.addStudent("Petar Ivanov", 20, "Group A");
        var student2 = service.addStudent("Stanislav Georgiev", 22, "Group A");
        var student3 = service.addStudent("Vasil Petrov", 19, "Group B");
        var student4 = service.addStudent("Vladimira Hristova", 23, "Group B");
        var student5 = service.addStudent("Maria Ilieva", 21, "Group A");

        System.out.println("Added students: " + service.getStudentCount());

        // Add teachers
        var teacher1 = service.addTeacher("Prof. Ivanov", 45);
        var teacher2 = service.addTeacher("Prof. Georgiev", 38);
        var teacher3 = service.addTeacher("Prof. Vasileva", 42);

        System.out.println("Added teachers: " + service.getTeacherCount());

        // Enroll students in courses
        service.enrollStudentInCourse(student1.getId(), mathCourse.getId());
        service.enrollStudentInCourse(student1.getId(), physicsCourse.getId());
        service.enrollStudentInCourse(student2.getId(), mathCourse.getId());
        service.enrollStudentInCourse(student2.getId(), artCourse.getId());
        service.enrollStudentInCourse(student3.getId(), physicsCourse.getId());
        service.enrollStudentInCourse(student3.getId(), musicCourse.getId());
        service.enrollStudentInCourse(student4.getId(), mathCourse.getId());
        service.enrollStudentInCourse(student4.getId(), artCourse.getId());
        service.enrollStudentInCourse(student5.getId(), physicsCourse.getId());

        // Assign teachers to courses
        service.assignTeacherToCourse(teacher1.getId(), mathCourse.getId());
        service.assignTeacherToCourse(teacher1.getId(), physicsCourse.getId());
        service.assignTeacherToCourse(teacher2.getId(), artCourse.getId());
        service.assignTeacherToCourse(teacher3.getId(), musicCourse.getId());

        // Generate reports
        System.out.println("\nReports:");
        System.out.println("Total students: " + service.getStudentCount());
        System.out.println("Total teachers: " + service.getTeacherCount());
        System.out.println("Courses by type: " + service.getCoursesByType().getCoursesByType());

        System.out.println("\nStudents in Mathematics course: " +
                service.getStudentsInCourse(mathCourse.getId()).size());
        System.out.println("Students in Group A: " +
                service.getStudentsInGroup("Group A").size());

        var groupCourseResult = service.getTeachersAndStudentsForGroupAndCourse("Group A", mathCourse.getId());
        System.out.println("Teachers and students for Group A in Mathematics: " +
                groupCourseResult.getTeachers().size() + " teachers, " +
                groupCourseResult.getStudents().size() + " students");

        var olderStudents = service.getStudentsOlderThanAgeInCourse(20, mathCourse.getId());
        System.out.println("Students older than 20 in Mathematics: " + olderStudents.size());

        System.out.println("\nDetailed Reports:");
        System.out.println("Students in Mathematics course:");
        service.getStudentsInCourse(mathCourse.getId()).forEach(s ->
                System.out.println(" - " + s.getName() + " (Age: " + s.getAge() + ", Group: " + s.getGroupName() + ")"));

        System.out.println("Students older than 20 in Mathematics:");
        olderStudents.forEach(s ->
                System.out.println(" - " + s.getName() + " (Age: " + s.getAge() + ", Group: " + s.getGroupName() + ")"));

        System.out.println("\nDemo completed successfully!");
        System.out.println("You can access the H2 console at: http://localhost:8080/h2-console");
        System.out.println("JDBC URL: jdbc:h2:mem:testdb");
        System.out.println("Username: sa");
        System.out.println("Password: (empty)");
    }
}
