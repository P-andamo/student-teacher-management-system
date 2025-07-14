package com.bytecraft.studentteachermanagement.dto;

import java.util.Map;

public class CoursesByTypeReport {
    private Map<String, Long> coursesByType;

    public CoursesByTypeReport(Map<String, Long> coursesByType) {
        this.coursesByType = coursesByType;
    }

    public Map<String, Long> getCoursesByType() {
        return coursesByType;
    }
}
