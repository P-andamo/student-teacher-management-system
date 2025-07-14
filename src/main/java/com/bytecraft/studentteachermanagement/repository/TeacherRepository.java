package com.bytecraft.studentteachermanagement.repository;

import com.bytecraft.studentteachermanagement.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {

    @Query("SELECT t FROM Teacher t JOIN t.courses c WHERE c.id = :courseId")
    List<Teacher> findTeachersInCourse(@Param("courseId") String courseId);
}
