package ru.hpclab.hl.module1.model;

import org.springframework.lang.NonNull;

import java.util.UUID;

import java.util.Date;

public class Student {

    @NonNull
    private UUID identifier;
    @NonNull
    private String firstName;
    @NonNull
    private String middleName;
    @NonNull
    private String lastName;
    @NonNull
    private String className;
    @NonNull
    private Date dateOfBirth;

    public Student(@NonNull UUID identifier, @NonNull String firstName,
                   @NonNull String middleName, @NonNull String lastName, @NonNull String className) {
        this.identifier = identifier;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.className = className;
        this.dateOfBirth = new Date();
    }

    @NonNull
    public UUID getIdentifier() {
        return identifier;
    }

    public void setIdentifier(@NonNull UUID identifier) {
        this.identifier = identifier;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    @NonNull
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(@NonNull String middleName) {
        this.middleName = middleName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
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
        return "Студент: " + identifier + ", " + firstName + ", " + middleName + ", " + lastName + ", " + className + ", " + dateOfBirth;

    }
}
