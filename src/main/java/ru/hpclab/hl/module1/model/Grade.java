package ru.hpclab.hl.module1.model;

import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.UUID;


public class Grade {

    private UUID gradeId;
    private Student student;
    private Subject subject;
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
        return subject;
    }

    public void setaSubject(@NonNull Subject aSubject) {
        this.subject = aSubject;
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
