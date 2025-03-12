package ru.hpclab.hl.module1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.model.Student;
import ru.hpclab.hl.module1.model.Subject;
import ru.hpclab.hl.module1.service.StudentService;
import ru.hpclab.hl.module1.service.SubjectService;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/{id}")
    public Subject getSubjectById(@PathVariable String id) {
        return subjectService.getSubjectById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable String id) {
        subjectService.deleteSubject(id);
    }

    @PostMapping(value = "/subject")
    public Subject saveSubject(@RequestBody Subject client) {
        return subjectService.saveSubject(client);
    }

    @PutMapping(value = "/{id}")
    public Subject updateSubject(@PathVariable(required = false) String id, @RequestBody Subject subject) {
        return subjectService.updateSubject(id, subject);
    }
}
