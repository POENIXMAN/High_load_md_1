package ru.hpclab.hl.module1.Entity;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID identifier;
    @NonNull
    private String FIO;

    private String className;
    @NonNull
    private Date dateOfBirth;

    @OneToMany(mappedBy = "studentEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GradeEntity> grades;

    public UUID getIdentifier() {
        return identifier;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    @NonNull
    public String getFIO() {
        return FIO;
    }

    public void setFIO(@NonNull String FIO) {
        this.FIO = FIO;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @NonNull
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(@NonNull Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<GradeEntity> getGrades() {
        return grades;
    }

    public void setGrades(List<GradeEntity> grades) {
        this.grades = grades;
    }

    public UUID getStudentId() {
        return identifier;
    }
}
