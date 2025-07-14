package com.bytecraft.studentteachermanagement.repository;

import com.bytecraft.studentteachermanagement.model.Course;
import com.bytecraft.studentteachermanagement.model.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

    @Query("SELECT COUNT(c) FROM Course c WHERE c.courseType = :courseType")
    long countByCourseType(CourseType courseType);
}
