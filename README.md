
Student-Teacher Management API Documentation
This API provides endpoints for managing students, teachers, and courses in an educational management system.
Base URL
/api

Course Management Endpoints
1. Add Course
   POST /courses
   Creates a new course in the system.
   Parameters:

name (String, required): Name of the course
courseType (CourseType, required): Type of the course (e.g., MAIN, ELECTIVE, OPTIONAL)

Response:

200 OK: Returns the created Course object
400 Bad Request: If parameters are invalid

Example:
httpPOST /api/courses?name=Mathematics&courseType=MAIN

2. Remove Course
   DELETE /courses/{courseId}
   Deletes a course from the system.
   Parameters:

courseId (String, path): Unique identifier of the course to remove

Response:

200 OK: Returns true if course was successfully removed, false otherwise
404 Not Found: If course doesn't exist

Example:
httpDELETE /api/courses/123e4567-e89b-12d3-a456-426614174000

3. Modify Course
   PUT /courses/{courseId}
   Updates an existing course's information.
   Parameters:

courseId (String, path): Unique identifier of the course to modify
name (String, optional): New name for the course
courseType (CourseType, optional): New type for the course

Response:

200 OK: Returns true if course was successfully modified, false otherwise
404 Not Found: If course doesn't exist

Example:
httpPUT /api/courses/123e4567-e89b-12d3-a456-426614174000?name=Advanced Mathematics

4. Get All Courses
   GET /courses
   Retrieves a list of all courses in the system.
   Response:

200 OK: Returns a list of Course objects

Example:
httpGET /api/courses

5. Get Course by ID
   GET /courses/{courseId}
   Retrieves a specific course by its unique identifier.
   Parameters:

courseId (String, path): Unique identifier of the course

Response:

200 OK: Returns the Course object
404 Not Found: If course doesn't exist

Example:
httpGET /api/courses/123e4567-e89b-12d3-a456-426614174000

Student Management Endpoints
6. Add Student
   POST /students
   Creates a new student in the system.
   Parameters:

name (String, required): Full name of the student
age (int, required): Age of the student
groupName (String, required): Name of the group/class the student belongs to

Response:

200 OK: Returns the created Student object
400 Bad Request: If parameters are invalid

Example:
httpPOST /api/students?name=John Doe&age=20&groupName=Group A

7. Remove Student
   DELETE /students/{studentId}
   Deletes a student from the system.
   Parameters:

studentId (String, path): Unique identifier of the student to remove

Response:

200 OK: Returns true if student was successfully removed, false otherwise
404 Not Found: If student doesn't exist

Example:
httpDELETE /api/students/456e7890-e89b-12d3-a456-426614174000

8. Modify Student
   PUT /students/{studentId}
   Updates an existing student's information.
   Parameters:

studentId (String, path): Unique identifier of the student to modify
name (String, optional): New name for the student
age (Integer, optional): New age for the student
groupName (String, optional): New group name for the student

Response:

200 OK: Returns true if student was successfully modified, false otherwise
404 Not Found: If student doesn't exist

Example:
httpPUT /api/students/456e7890-e89b-12d3-a456-426614174000?name=John Smith&age=21

9. Enroll Student in Course
   POST /students/{studentId}/enroll/{courseId}
   Enrolls a student in a specific course.
   Parameters:

studentId (String, path): Unique identifier of the student
courseId (String, path): Unique identifier of the course

Response:

200 OK: Returns true if enrollment was successful, false otherwise
404 Not Found: If student or course doesn't exist
409 Conflict: If student is already enrolled in the course

Example:
httpPOST /api/students/456e7890-e89b-12d3-a456-426614174000/enroll/123e4567-e89b-12d3-a456-426614174000

10. Unenroll Student from Course
    DELETE /students/{studentId}/enroll/{courseId}
    Removes a student's enrollment from a specific course.
    Parameters:

studentId (String, path): Unique identifier of the student
courseId (String, path): Unique identifier of the course

Response:

200 OK: Returns true if unenrollment was successful, false otherwise
404 Not Found: If student or course doesn't exist
400 Bad Request: If student is not enrolled in the course

Example:
httpDELETE /api/students/456e7890-e89b-12d3-a456-426614174000/enroll/123e4567-e89b-12d3-a456-426614174000

11. Get All Students
    GET /students
    Retrieves a list of all students in the system.
    Response:

200 OK: Returns a list of Student objects

Example:
httpGET /api/students

12. Get Student by ID
    GET /students/{studentId}
    Retrieves a specific student by their unique identifier.
    Parameters:

studentId (String, path): Unique identifier of the student

Response:

200 OK: Returns the Student object
404 Not Found: If student doesn't exist

Example:
httpGET /api/students/456e7890-e89b-12d3-a456-426614174000

Teacher Management Endpoints
13. Add Teacher
    POST /teachers
    Creates a new teacher in the system.
    Parameters:

name (String, required): Full name of the teacher
age (int, required): Age of the teacher

Response:

200 OK: Returns the created Teacher object
400 Bad Request: If parameters are invalid

Example:
httpPOST /api/teachers?name=Dr. Jane Smith&age=45

14. Remove Teacher
    DELETE /teachers/{teacherId}
    Deletes a teacher from the system.
    Parameters:

teacherId (String, path): Unique identifier of the teacher to remove

Response:

200 OK: Returns true if teacher was successfully removed, false otherwise
404 Not Found: If teacher doesn't exist

Example:
httpDELETE /api/teachers/789e1234-e89b-12d3-a456-426614174000

15. Modify Teacher
    PUT /teachers/{teacherId}
    Updates an existing teacher's information.
    Parameters:

teacherId (String, path): Unique identifier of the teacher to modify
name (String, optional): New name for the teacher
age (Integer, optional): New age for the teacher

Response:

200 OK: Returns true if teacher was successfully modified, false otherwise
404 Not Found: If teacher doesn't exist

Example:
httpPUT /api/teachers/789e1234-e89b-12d3-a456-426614174000?name=Dr. Jane Johnson&age=46

16. Assign Teacher to Course
    POST /teachers/{teacherId}/assign/{courseId}
    Assigns a teacher to teach a specific course.
    Parameters:

teacherId (String, path): Unique identifier of the teacher
courseId (String, path): Unique identifier of the course

Response:

200 OK: Returns true if assignment was successful, false otherwise
404 Not Found: If teacher or course doesn't exist
409 Conflict: If teacher is already assigned to the course

Example:
httpPOST /api/teachers/789e1234-e89b-12d3-a456-426614174000/assign/123e4567-e89b-12d3-a456-426614174000

17. Unassign Teacher from Course
    DELETE /teachers/{teacherId}/assign/{courseId}
    Removes a teacher's assignment from a specific course.
    Parameters:

teacherId (String, path): Unique identifier of the teacher
courseId (String, path): Unique identifier of the course

Response:

200 OK: Returns true if unassignment was successful, false otherwise
404 Not Found: If teacher or course doesn't exist
400 Bad Request: If teacher is not assigned to the course

Example:
httpDELETE /api/teachers/789e1234-e89b-12d3-a456-426614174000/assign/123e4567-e89b-12d3-a456-426614174000

18. Get All Teachers
    GET /teachers
    Retrieves a list of all teachers in the system.
    Response:

200 OK: Returns a list of Teacher objects

Example:
httpGET /api/teachers

19. Get Teacher by ID
    GET /teachers/{teacherId}
    Retrieves a specific teacher by their unique identifier.
    Parameters:

teacherId (String, path): Unique identifier of the teacher

Response:

200 OK: Returns the Teacher object
404 Not Found: If teacher doesn't exist

Example:
httpGET /api/teachers/789e1234-e89b-12d3-a456-426614174000

Report Endpoints
20. Get Student Count
    GET /reports/student-count
    Retrieves the total number of students in the system.
    Response:

200 OK: Returns the count as a Long value

Example:
httpGET /api/reports/student-count

21. Get Teacher Count
    GET /reports/teacher-count
    Retrieves the total number of teachers in the system.
    Response:

200 OK: Returns the count as a Long value

Example:
httpGET /api/reports/teacher-count

22. Get Courses by Type
    GET /reports/courses-by-type
    Retrieves a report showing the distribution of courses by their type.
    Response:

200 OK: Returns a CoursesByTypeReport object containing counts for each course type

Example:
httpGET /api/reports/courses-by-type

23. Get Students in Course
    GET /reports/students-in-course/{courseId}
    Retrieves all students enrolled in a specific course.
    Parameters:

courseId (String, path): Unique identifier of the course

Response:

200 OK: Returns a list of Student objects enrolled in the course
404 Not Found: If course doesn't exist

Example:
httpGET /api/reports/students-in-course/123e4567-e89b-12d3-a456-426614174000

24. Get Students in Group
    GET /reports/students-in-group/{groupName}
    Retrieves all students belonging to a specific group.
    Parameters:

groupName (String, path): Name of the group

Response:

200 OK: Returns a list of Student objects in the specified group

Example:
httpGET /api/reports/students-in-group/Group A

25. Get Teachers and Students for Group and Course
    GET /reports/group-course/{groupName}/{courseId}
    Retrieves a comprehensive report showing both teachers and students for a specific group and course combination.
    Parameters:

groupName (String, path): Name of the group
courseId (String, path): Unique identifier of the course

Response:

200 OK: Returns a GroupCourseReport object containing teachers and students data
404 Not Found: If course doesn't exist

Example:
httpGET /api/reports/group-course/Group A/123e4567-e89b-12d3-a456-426614174000

26. Get Students Older Than Age in Course
    GET /reports/students-older-than/{minAge}/in-course/{courseId}
    Retrieves students who are older than a specified age and are enrolled in a specific course.
    Parameters:

minAge (int, path): Minimum age threshold
courseId (String, path): Unique identifier of the course

Response:

200 OK: Returns a list of Student objects meeting the criteria
404 Not Found: If course doesn't exist

Example:
httpGET /api/reports/students-older-than/21/in-course/123e4567-e89b-12d3-a456-426614174000