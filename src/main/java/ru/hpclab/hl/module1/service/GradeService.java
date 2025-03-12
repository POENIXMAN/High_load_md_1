package ru.hpclab.hl.module1.service;

import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.controller.exeption.UserException;
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
        return gradeRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new UserException("Grade with ID " + id + " not found"));
    }

    public Grade saveGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    public void deleteGrade(String id) {
        gradeRepository.deleteById(UUID.fromString(id));
    }

    public Grade updateGrade(String id, Grade grade) {
        grade.setGradeId(UUID.fromString(id));
        return gradeRepository.save(grade);
    }

    public double calculateAverageGradeForClass(UUID subjectId, int year) {
        Date startDate = getStartOfYear(year);
        Date endDate = getEndOfYear(year);

        List<Grade> grades = gradeRepository.findBySubjectAndGradingDateBetween(subjectId, startDate, endDate);

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
