package ru.hpclab.hl.module1.model;

import org.springframework.lang.NonNull;

import java.util.UUID;

public class Subject {

    @NonNull
    private UUID identifier;
    @NonNull
    private String className;
    @NonNull
    private String teacherName;
    @NonNull
    private int roomNumber;


    public Subject(@NonNull UUID identifier, @NonNull String className, @NonNull String teacherName, int roomNumber) {
        this.identifier = identifier;
        this.className = className;
        this.roomNumber = roomNumber;
        this.teacherName = teacherName;
    }

    @NonNull
    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(@NonNull String teacherName) {
        this.teacherName = teacherName;
    }

    @NonNull
    public UUID getIdentifier() {
        return identifier;
    }

    public void setIdentifier(@NonNull UUID identifier) {
        this.identifier = identifier;
    }

    @NonNull
    public String getClassName() {
        return className;
    }

    public void setClassName(@NonNull String className) {
        this.className = className;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
