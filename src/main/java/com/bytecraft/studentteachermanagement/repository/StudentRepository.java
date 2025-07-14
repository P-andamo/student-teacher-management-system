package com.bytecraft.studentteachermanagement.repository;

import com.bytecraft.studentteachermanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    List<Student> findByGroupName(String groupName);

    @Query("SELECT s FROM Student s JOIN s.courses c WHERE c.id = :courseId")
    List<Student> findStudentsInCourse(@Param("courseId") String courseId);

    @Query("SELECT s FROM Student s JOIN s.courses c WHERE s.groupName = :groupName AND c.id = :courseId")
    List<Student> findStudentsInGroupAndCourse(@Param("groupName") String groupName, @Param("courseId") String courseId);

    @Query("SELECT s FROM Student s JOIN s.courses c WHERE s.age > :minAge AND c.id = :courseId")
    List<Student> findStudentsOlderThanAgeInCourse(@Param("minAge") int minAge, @Param("courseId") String courseId);
}
