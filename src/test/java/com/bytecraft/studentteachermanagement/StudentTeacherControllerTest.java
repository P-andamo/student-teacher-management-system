package com.bytecraft.studentteachermanagement;


import com.bytecraft.studentteachermanagement.controller.StudentTeacherController;
import com.bytecraft.studentteachermanagement.model.CourseType;
import com.bytecraft.studentteachermanagement.service.StudentTeacherManagementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentTeacherController.class)
public class StudentTeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentTeacherManagementService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddCourse() throws Exception {
        when(service.addCourse(anyString(), any(CourseType.class)))
                .thenReturn(new com.bytecraft.studentteachermanagement.model.Course("Mathematics", CourseType.MAIN));

        mockMvc.perform(post("/api/courses")
                        .param("name", "Mathematics")
                        .param("courseType", "MAIN"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mathematics"))
                .andExpect(jsonPath("$.courseType").value("MAIN"));
    }

    @Test
    public void testAddStudent() throws Exception {
        when(service.addStudent(anyString(), anyInt(), anyString()))
                .thenReturn(new com.bytecraft.studentteachermanagement.model.Student("John Doe", 20, "Group A"));

        mockMvc.perform(post("/api/students")
                        .param("name", "John Doe")
                        .param("age", "20")
                        .param("groupName", "Group A"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.age").value(20))
                .andExpect(jsonPath("$.groupName").value("Group A"));
    }

    @Test
    public void testAddTeacher() throws Exception {
        when(service.addTeacher(anyString(), anyInt()))
                .thenReturn(new com.bytecraft.studentteachermanagement.model.Teacher("Jane Smith", 35));

        mockMvc.perform(post("/api/teachers")
                        .param("name", "Jane Smith")
                        .param("age", "35"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Smith"))
                .andExpect(jsonPath("$.age").value(35));
    }

    @Test
    public void testGetStudentCount() throws Exception {
        when(service.getStudentCount()).thenReturn(5L);
        mockMvc.perform(get("/api/reports/student-count"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));
    }

    @Test
    public void testGetTeacherCount() throws Exception {
        when(service.getTeacherCount()).thenReturn(3L);
        mockMvc.perform(get("/api/reports/teacher-count"))
                .andExpect(status().isOk())
                .andExpect(content().string("3"));
    }

    @Test
    public void testEnrollStudentInCourse() throws Exception {
        when(service.enrollStudentInCourse(anyString(), anyString())).thenReturn(true);
        mockMvc.perform(post("/api/students/student-id/enroll/course-id"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void testAssignTeacherToCourse() throws Exception {
        when(service.assignTeacherToCourse(anyString(), anyString())).thenReturn(true);

        mockMvc.perform(post("/api/teachers/teacher-id/assign/course-id"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}
