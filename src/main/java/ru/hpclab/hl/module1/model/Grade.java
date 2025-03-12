package ru.hpclab.hl.module1.model;

import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.UUID;

public class Grade {


    @NonNull
    private UUID gradeId;
    @NonNull
    private Student student;
    @NonNull
    private Subject aSubject;
    @NonNull
    private int gradeValue;
    @NonNull
    private Date gradingDate;


    @NonNull
    public UUID getGradeId() {
        return gradeId;
    }

    public void setGradeId(@NonNull UUID gradeId) {
        this.gradeId = gradeId;
    }

    @NonNull
    public Student getStudent() {
        return student;
    }

    public void setStudent(@NonNull Student student) {
        this.student = student;
    }

    @NonNull
    public Subject getaSubject() {
        return aSubject;
    }

    public void setaSubject(@NonNull Subject aSubject) {
        this.aSubject = aSubject;
    }

    public int getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(int gradeValue) {
        this.gradeValue = gradeValue;
    }

    @NonNull
    public Date getGradingDate() {
        return gradingDate;
    }

    public void setGradingDate(@NonNull Date gradingDate) {
        this.gradingDate = gradingDate;
    }


}
