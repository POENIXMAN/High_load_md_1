package ru.hpclab.hl.module1.repository;

import org.apache.catalina.User;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import ru.hpclab.hl.module1.controller.exeption.UserException;
import ru.hpclab.hl.module1.model.Grade;
import ru.hpclab.hl.module1.model.Student;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Repository
public class GradeRepository {

    public static final String GRADE_NOT_FOUND_MSG = "Grade with ID %s not found";
    public static final String GRADE_EXISTS_MSG = "Grade with ID %s already exists";

    private final Map<UUID, Grade> grades = new HashMap<>();

    public Grade findById(UUID id) {
        final var grade = grades.get(id);
        if (grade == null) {
            throw new UserException(format(GRADE_NOT_FOUND_MSG, id));
        }
        return grade;
    }

    public Grade save(Grade grade) {
        if (ObjectUtils.isEmpty(grade.getGradeId())) {
            grade.setGradeId(UUID.randomUUID());
        }

        final var existingGrade = grades.get(grade.getGradeId());
        if (existingGrade != null) {
            throw new UserException(format(GRADE_EXISTS_MSG, grade.getGradeId()));
        }

        grades.put(grade.getGradeId(), grade);

        return grade;
    }

    public Grade put(Grade grade) {
        final var existingGrade = grades.get(grade.getGradeId());
        if (existingGrade == null) {
            throw new UserException(format(GRADE_NOT_FOUND_MSG, grade.getGradeId()));
        }
        final var removed = grades.remove(grade.getGradeId());
        if (removed != null) {
            grades.put(grade.getGradeId(), grade);
        } else {
            throw new UserException(format(GRADE_NOT_FOUND_MSG, grade.getGradeId()));
        }
        return grade;
    }


    public void delete(UUID id) {
        final var removed = grades.remove(id);
        if (removed == null) {
            throw new UserException(format(GRADE_NOT_FOUND_MSG, id));
        }
    }

    public void clear() {
        grades.clear();
    }

    public List<Grade> findAll() {
        return new ArrayList<>(grades.values());
    }

    public List<Grade> findByStudentIdentifierAndASubjectIdentifierAndGradingDateBetween(
            UUID studentId, UUID subjectId, Date startOfYear, Date endOfYear) {

        return grades.values().stream()
                .filter(grade -> grade.getStudent().getIdentifier().equals(studentId))
                .filter(grade -> grade.getaSubject().getIdentifier().equals(subjectId))
                .filter(grade -> !grade.getGradingDate().before(startOfYear) && !grade.getGradingDate().after(endOfYear))
                .collect(Collectors.toList());
    }
}
