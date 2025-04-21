package ru.hpclab.hl.module1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import ru.hpclab.hl.module1.Application;
import ru.hpclab.hl.module1.model.Student;
import ru.hpclab.hl.module1.repository.StudentRepository;
import ru.hpclab.hl.module1.service.StudentService;


import java.util.Date;
import java.util.UUID;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class StudentControllerTest {

//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    private StudentService studentService;
//
//    @BeforeEach
//    public void init() {
//        // Clear all students before each test
//        studentService.deleteAllStudents();
//    }
//
//    /**
//     * Test for retrieving all students.
//     */
//    @Test
//    public void getAllStudents_shouldReturnAllStudents_whenStudentsExist() throws Exception {
//        // Create and save a student
//        Student student = new Student();
//        student.setIdentifier(UUID.randomUUID());
//        student.setFIO("John Doe");
//        student.setClassName("10A");
//        student.setDateOfBirth(new Date());
//        studentService.saveStudent(student);
//
//        // Perform GET request to retrieve all students
//        mvc.perform(get("/students").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1))) // Expect one student in the list
//                .andExpect((ResultMatcher) jsonPath("$[0].FIO", is("John Doe")));
//    }
//
//    /**
//     * Test for retrieving a student by ID.
//     */
//    @Test
//    public void getStudentById_shouldReturnStudent_whenStudentExists() throws Exception {
//        // Create and save a student
//        Student student = new Student();
//        UUID studentId = UUID.randomUUID();
//        student.setIdentifier(studentId);
//        student.setFIO("Jane Smith");
//        student.setClassName("9B");
//        student.setDateOfBirth(new Date());
//        studentService.saveStudent(student);
//
//        // Perform GET request to retrieve the student by ID
//        mvc.perform(get("/students/{id}", studentId.toString()).accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) jsonPath("$.FIO", is("Jane Smith")))
//                .andExpect((ResultMatcher) jsonPath("$.className", is("9B")));
//    }
//
//    /**
//     * Test for saving a new student.
//     */
//    @Test
//    public void saveStudent_shouldCreateNewStudent_whenValidDataProvided() throws Exception {
//        // Create a new student object
//        Student student = new Student();
//        student.setFIO("Alice Johnson");
//        student.setClassName("8C");
//        student.setDateOfBirth(new Date());
//
//        // Convert the student object to JSON
//        String studentJson = objectMapper.writeValueAsString(student);
//
//        // Perform POST request to save the student
//        mvc.perform(post("/students/student")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(studentJson))
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) jsonPath("$.FIO", is("Alice Johnson")))
//                .andExpect((ResultMatcher) jsonPath("$.className", is("8C")));
//    }
//
//    /**
//     * Test for updating an existing student.
//     */
//    @Test
//    public void updateStudent_shouldUpdateStudent_whenValidDataProvided() throws Exception {
//        // Create and save a student
//        Student student = new Student();
//        UUID studentId = UUID.randomUUID();
//        student.setIdentifier(studentId);
//        student.setFIO("Bob Brown");
//        student.setClassName("7D");
//        student.setDateOfBirth(new Date());
//        studentService.saveStudent(student);
//
//        // Update the student's details
//        Student updatedStudent = new Student();
//        updatedStudent.setFIO("Robert Brown");
//        updatedStudent.setClassName("7E");
//        updatedStudent.setDateOfBirth(new Date());
//
//        // Convert the updated student object to JSON
//        String updatedStudentJson = objectMapper.writeValueAsString(updatedStudent);
//
//        // Perform PUT request to update the student
//        mvc.perform(put("/students/{id}", studentId.toString())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(updatedStudentJson))
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) jsonPath("$.FIO", is("Robert Brown")))
//                .andExpect((ResultMatcher) jsonPath("$.className", is("7E")));
//    }
//
//    /**
//     * Test for deleting a student.
//     */
//    @Test
//    public void deleteStudent_shouldDeleteStudent_whenStudentExists() throws Exception {
//        // Create and save a student
//        Student student = new Student();
//        UUID studentId = UUID.randomUUID();
//        student.setIdentifier(studentId);
//        student.setFIO("Charlie Davis");
//        student.setClassName("6F");
//        student.setDateOfBirth(new Date());
//        studentService.saveStudent(student);
//
//        // Perform DELETE request to delete the student
//        mvc.perform(delete("/students/{id}", studentId.toString()))
//                .andExpect(status().isOk());
//
//        // Verify that the student no longer exists
//        mvc.perform(get("/students/{id}", studentId.toString()))
//                .andExpect(status().isNotFound());
//    }
}
