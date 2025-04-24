package ru.hpclab.hl.module1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.DTO.StudentDTO;
import ru.hpclab.hl.module1.service.ObservabilityService;
import ru.hpclab.hl.module1.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    private final ObservabilityService observabilityService;


    @Autowired
    public StudentController(StudentService studentService, ObservabilityService observabilityService) {
        this.studentService = studentService;
        this.observabilityService = observabilityService;
    }

    @GetMapping
    public List<StudentDTO> getStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentDTO getStudentById(@PathVariable String id) {
        return studentService.getStudentById(id);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearAllStudents() {
        studentService.clearAllStudents();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
    }

    @PostMapping(value = "/student")
    public StudentDTO saveStudent(@RequestBody StudentDTO client) {
        observabilityService.start("controller.createNewStudent");
        try {
            return studentService.saveStudent(client);
        } finally {
            observabilityService.stop("controller.createNewStudent");
        }
    }

    @PutMapping(value = "/{id}")
    public StudentDTO updateStudent(@PathVariable(required = false) String id, @RequestBody StudentDTO student) {
        return studentService.updateStudent(id, student);
    }

}
