<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Student-Teacher Management API Documentation</title>
  <style>
    body { font-family: Arial, sans-serif; line-height: 1.6; margin: 40px; background-color: #f9f9f9; }
    h1 { color: #2c3e50; border-bottom: 2px solid #ccc; padding-bottom: 0.2em; }
    h2 { color: #34495e; margin-top: 40px; }
    h3 { color: #2d3436; }
    pre { background: #ecf0f1; padding: 10px; border-left: 4px solid #2980b9; }
    code { font-family: Consolas, monospace; }
    .section { margin-bottom: 30px; }
    .endpoint { background-color: #fff; padding: 20px; border-radius: 6px; box-shadow: 0 0 10px rgba(0,0,0,0.05); }
  </style>
</head>
<body>

  <h1>Student-Teacher Management API Documentation</h1>
  <p>This API provides endpoints for managing students, teachers, and courses in an educational management system.</p>
  <p><strong>Base URL:</strong> <code>/api</code></p>

  <h2>Course Management Endpoints</h2>

  <div class="section endpoint">
    <h3>1. Add Course</h3>
    <p><strong>POST</strong> <code>/courses</code></p>
    <p><strong>Parameters:</strong></p>
    <ul>
      <li><code>name</code> (String, required): Name of the course</li>
      <li><code>courseType</code> (CourseType, required): Type of the course (MAIN, ELECTIVE, OPTIONAL)</li>
    </ul>
    <p><strong>Response:</strong></p>
    <ul>
      <li><code>200 OK</code>: Returns created Course object</li>
      <li><code>400 Bad Request</code>: If parameters are invalid</li>
    </ul>
    <pre>POST /api/courses?name=Mathematics&amp;courseType=MAIN</pre>
  </div>

  <div class="section endpoint">
    <h3>2. Remove Course</h3>
    <p><strong>DELETE</strong> <code>/courses/{courseId}</code></p>
    <p><strong>Response:</strong></p>
    <ul>
      <li><code>200 OK</code>: Returns true if deleted, false otherwise</li>
      <li><code>404 Not Found</code>: If course doesn't exist</li>
    </ul>
    <pre>DELETE /api/courses/123e4567-e89b-12d3-a456-426614174000</pre>
  </div>

  <div class="section endpoint">
    <h3>3. Modify Course</h3>
    <p><strong>PUT</strong> <code>/courses/{courseId}</code></p>
    <ul>
      <li><code>name</code> (optional)</li>
      <li><code>courseType</code> (optional)</li>
    </ul>
    <p><strong>Response:</strong></p>
    <ul>
      <li><code>200 OK</code>: True if modified</li>
      <li><code>404 Not Found</code>: If not found</li>
    </ul>
    <pre>PUT /api/courses/123e4567-e89b-12d3-a456-426614174000?name=Advanced Mathematics</pre>
  </div>

  <div class="section endpoint">
    <h3>4. Get All Courses</h3>
    <p><strong>GET</strong> <code>/courses</code></p>
    <p><strong>Response:</strong> <code>200 OK</code>: List of Course objects</p>
    <pre>GET /api/courses</pre>
  </div>

  <div class="section endpoint">
    <h3>5. Get Course by ID</h3>
    <p><strong>GET</strong> <code>/courses/{courseId}</code></p>
    <ul>
      <li><code>200 OK</code>: Course object</li>
      <li><code>404 Not Found</code></li>
    </ul>
    <pre>GET /api/courses/123e4567-e89b-12d3-a456-426614174000</pre>
  </div>

  <h2>Student Management Endpoints</h2>

  <div class="section endpoint">
    <h3>6. Add Student</h3>
    <p><strong>POST</strong> <code>/students</code></p>
    <ul>
      <li><code>name</code> (String, required)</li>
      <li><code>age</code> (int, required)</li>
      <li><code>groupName</code> (String, required)</li>
    </ul>
    <pre>POST /api/students?name=John Doe&amp;age=20&amp;groupName=Group A</pre>
  </div>

  <div class="section endpoint">
    <h3>7. Remove Student</h3>
    <p><strong>DELETE</strong> <code>/students/{studentId}</code></p>
    <pre>DELETE /api/students/456e7890-e89b-12d3-a456-426614174000</pre>
  </div>

  <div class="section endpoint">
    <h3>8. Modify Student</h3>
    <p><strong>PUT</strong> <code>/students/{studentId}</code></p>
    <pre>PUT /api/students/456e7890-e89b-12d3-a456-426614174000?name=John Smith&amp;age=21</pre>
  </div>

  <div class="section endpoint">
    <h3>9. Enroll Student in Course</h3>
    <p><strong>POST</strong> <code>/students/{studentId}/enroll/{courseId}</code></p>
    <pre>POST /api/students/456e7890-e89b-12d3-a456-426614174000/enroll/123e4567-e89b-12d3-a456-426614174000</pre>
  </div>

  <div class="section endpoint">
    <h3>10. Unenroll Student from Course</h3>
    <p><strong>DELETE</strong> <code>/students/{studentId}/enroll/{courseId}</code></p>
    <pre>DELETE /api/students/456e7890-e89b-12d3-a456-426614174000/enroll/123e4567-e89b-12d3-a456-426614174000</pre>
  </div>

  <div class="section endpoint">
    <h3>11. Get All Students</h3>
    <p><strong>GET</strong> <code>/students</code></p>
    <pre>GET /api/students</pre>
  </div>

  <div class="section endpoint">
    <h3>12. Get Student by ID</h3>
    <pre>GET /api/students/456e7890-e89b-12d3-a456-426614174000</pre>
  </div>

  <h2>Teacher Management Endpoints</h2>

  <div class="section endpoint">
    <h3>13. Add Teacher</h3>
    <pre>POST /api/teachers?name=Dr. Jane Smith&amp;age=45</pre>
  </div>

  <div class="section endpoint">
    <h3>14. Remove Teacher</h3>
    <pre>DELETE /api/teachers/789e1234-e89b-12d3-a456-426614174000</pre>
  </div>

  <div class="section endpoint">
    <h3>15. Modify Teacher</h3>
    <pre>PUT /api/teachers/789e1234-e89b-12d3-a456-426614174000?name=Dr. Jane Johnson&amp;age=46</pre>
  </div>

  <div class="section endpoint">
    <h3>16. Assign Teacher to Course</h3>
    <pre>POST /api/teachers/789e1234-e89b-12d3-a456-426614174000/assign/123e4567-e89b-12d3-a456-426614174000</pre>
  </div>

  <div class="section endpoint">
    <h3>17. Unassign Teacher from Course</h3>
    <pre>DELETE /api/teachers/789e1234-e89b-12d3-a456-426614174000/assign/123e4567-e89b-12d3-a456-426614174000</pre>
  </div>

  <div class="section endpoint">
    <h3>18. Get All Teachers</h3>
    <pre>GET /api/teachers</pre>
  </div>

  <div class="section endpoint">
    <h3>19. Get Teacher by ID</h3>
    <pre>GET /api/teachers/789e1234-e89b-12d3-a456-426614174000</pre>
  </div>

  <h2>Report Endpoints</h2>

  <div class="section endpoint">
    <h3>20. Get Student Count</h3>
    <pre>GET /api/reports/student-count</pre>
  </div>

  <div class="section endpoint">
    <h3>21. Get Teacher Count</h3>
    <pre>GET /api/reports/teacher-count</pre>
  </div>

  <div class="section endpoint">
    <h3>22. Get Courses by Type</h3>
    <pre>GET /api/reports/courses-by-type</pre>
  </div>

  <div class="section endpoint">
    <h3>23. Get Students in Course</h3>
    <pre>GET /api/reports/students-in-course/123e4567-e89b-12d3-a456-426614174000</pre>
  </div>

  <div class="section endpoint">
    <h3>24. Get Students in Group</h3>
    <pre>GET /api/reports/students-in-group/Group A</pre>
  </div>

  <div class="section endpoint">
    <h3>25. Get Teachers and Students for Group and Course</h3>
    <pre>GET /api/reports/group-course/Group A/123e4567-e89b-12d3-a456-426614174000</pre>
  </div>

  <div class="section endpoint">
    <h3>26. Get Students Older Than Age in Course</h3>
    <pre>GET /api/reports/students-older-than/21/in-course/123e4567-e89b-12d3-a456-426614174000</pre>
  </div>

</body>
</html>
