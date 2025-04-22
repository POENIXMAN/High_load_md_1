package ru.hpclab.hl.module1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.DTO.GradeDTO;


import ru.hpclab.hl.module1.service.GradeService;
import ru.hpclab.hl.module1.service.StudentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/grades")
public class GradeController {
    private final GradeService gradeService;

    @Autowired
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping
    public List<GradeDTO> getGrades() {
        return gradeService.getAllGrades();
    }

    @GetMapping("/{id}")
    public GradeDTO getGradeById(@PathVariable String id) {
        return gradeService.getGradeById(id);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearAllGrades(){
        gradeService.clearAllGrades();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public void deleteGrade(@PathVariable String id) {
        gradeService.deleteGrade(id);
    }

    @PostMapping(value = "/grade")
    public GradeDTO saveGrade(@RequestBody GradeDTO grade) {
        return gradeService.saveGrade(grade);
    }

    @PutMapping(value = "/{id}")
    public GradeDTO updateGrade(@PathVariable(required = false) String id, @RequestBody GradeDTO grade) {
        return gradeService.updateGrade(id, grade);
    }

    @GetMapping("/average/class/{subjectId}/year/{year}")
    public double calculateAverageGradeForClass(
            @PathVariable UUID subjectId,
            @PathVariable int year) {
        System.out.println("Received calculation request for subject " + subjectId + " and year " + year);
        return gradeService.calculateAverageGradeForClass(subjectId, year);
    }
}
