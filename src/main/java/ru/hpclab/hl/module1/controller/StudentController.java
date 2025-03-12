package ru.hpclab.hl.module1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.model.Student;
import ru.hpclab.hl.module1.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable String id) {
        return studentService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable String id) {
        studentService.deleteUser(id);
    }

    @PostMapping(value = "/student")
    public Student saveStudent(@RequestBody Student client) {
        return studentService.saveUser(client);
    }

    @PutMapping(value = "/{id}")
    public Student updateStudent(@PathVariable(required = false) String id, @RequestBody Student student) {
        return studentService.updateUser(id, student);
    }

}
