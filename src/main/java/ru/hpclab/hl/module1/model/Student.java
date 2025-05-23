package ru.hpclab.hl.module1.model;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;
import ru.hpclab.hl.module1.Entity.GradeEntity;

import java.util.List;
import java.util.UUID;

import java.util.Date;



public class Student {


    private UUID identifier;
    @NonNull
    private String FIO;

    private String className;
    @NonNull
    private Date dateOfBirth;


    private List<Grade> grades;

    public Student(@NonNull UUID identifier, @NonNull String FIO, @NonNull String className) {
        this.identifier = identifier;
        this.FIO = FIO;
        this.className = className;
        this.dateOfBirth = new Date();
    }

    public Student() {

    }

    @NonNull
    public UUID getIdentifier() {
        return identifier;
    }

    public void setIdentifier(@NonNull UUID identifier) {
        this.identifier = identifier;
    }

    @NonNull
    public String getFIO() {
        return FIO;
    }

    public void setFIO(@NonNull String FIO) {
        this.FIO = FIO;
    }

    @NonNull
    public String getClassName() {
        return className;
    }

    public void setClassName(@NonNull String className) {
        this.className = className;
    }

    @NonNull
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(@NonNull Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Студент: " + identifier + ", " + FIO + ", " + className + ", " + dateOfBirth;

    }
}
