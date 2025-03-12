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
        return studentService.getStudentById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
    }

    @PostMapping(value = "/student")
    public Student saveStudent(@RequestBody Student client) {
        return studentService.saveStudent(client);
    }

    @PutMapping(value = "/{id}")
    public Student updateStudent(@PathVariable(required = false) String id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

}
