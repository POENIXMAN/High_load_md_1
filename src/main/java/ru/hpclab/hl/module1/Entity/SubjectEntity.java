package ru.hpclab.hl.module1.Entity;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "subject_entity")
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID identifier;
    @NonNull
    private String className;
    @NonNull
    private String teacherName;
    @NonNull
    private int roomNumber;

    @OneToMany(mappedBy = "subjectEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GradeEntity> grades;

    public UUID getIdentifier() {
        return identifier;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    @NonNull
    public String getClassName() {
        return className;
    }

    public void setClassName(@NonNull String className) {
        this.className = className;
    }

    @NonNull
    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(@NonNull String teacherName) {
        this.teacherName = teacherName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public List<GradeEntity> getGrades() {
        return grades;
    }

    public void setGrades(List<GradeEntity> grades) {
        this.grades = grades;
    }

    public UUID getSubjectId() {
        return identifier;
    }
}
