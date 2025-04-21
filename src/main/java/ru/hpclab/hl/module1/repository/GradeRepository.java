package ru.hpclab.hl.module1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.hpclab.hl.module1.Entity.GradeEntity;
import ru.hpclab.hl.module1.model.Grade;

import java.util.*;


@Repository
public interface GradeRepository extends JpaRepository<GradeEntity, UUID> {


    @Query("SELECT g FROM GradeEntity g WHERE g.subjectEntity.identifier = :subjectId " +
            "AND g.gradingDate BETWEEN :startDate AND :endDate")
    List<GradeEntity> findBySubjectAndGradingDateBetween(
            @Param("subjectId") UUID subjectId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);
}
