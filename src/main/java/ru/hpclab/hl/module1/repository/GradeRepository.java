package ru.hpclab.hl.module1.repository;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import ru.hpclab.hl.module1.controller.exeption.UserException;
import ru.hpclab.hl.module1.model.Grade;
import ru.hpclab.hl.module1.model.Student;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Repository
public interface GradeRepository extends JpaRepository<Grade, UUID> {


    @Query("SELECT g FROM Grade g WHERE g.subject.identifier = :subjectId " +
            "AND g.gradingDate BETWEEN :startDate AND :endDate")
    List<Grade> findBySubjectAndGradingDateBetween(
            @Param("subjectId") UUID subjectId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);
}
