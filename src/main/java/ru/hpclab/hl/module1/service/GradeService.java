package ru.hpclab.hl.module1.service;

import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.model.Grade;
import ru.hpclab.hl.module1.repository.GradeRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;

    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    public Grade getGradeById(String id) {
        return gradeRepository.findById(UUID.fromString(id));
    }

    public Grade saveGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    public void deleteGrade(String id) {
        gradeRepository.delete(UUID.fromString(id));
    }

    public Grade updateGrade(String id, Grade grade) {
        grade.setGradeId(UUID.fromString(id));
        return gradeRepository.put(grade);
    }

    public double calculateAverageGradeBySubjectAndYear(UUID studentId, UUID subjectId, int year) {
        List<Grade> grades = gradeRepository.findByStudentIdentifierAndASubjectIdentifierAndGradingDateBetween(
                studentId, subjectId, getStartOfYear(year), getEndOfYear(year));

        if (grades.isEmpty()) {
            return 0.0;
        }

        double sum = grades.stream().mapToInt(Grade::getGradeValue).sum();
        return sum / grades.size();
    }

    private Date getStartOfYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, Calendar.JANUARY, 1, 0, 0, 0);
        return calendar.getTime();
    }

    private Date getEndOfYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
        return calendar.getTime();
    }
}
