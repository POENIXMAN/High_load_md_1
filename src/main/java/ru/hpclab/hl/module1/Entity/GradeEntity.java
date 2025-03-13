package ru.hpclab.hl.module1.Entity;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;
import ru.hpclab.hl.module1.model.Student;
import ru.hpclab.hl.module1.model.Subject;

import java.util.Date;
import java.util.UUID;

@Entity
public class GradeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID gradeId;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private StudentEntity studentEntity;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private SubjectEntity subjectEntity;
    @NonNull
    private int gradeValue;
    @NonNull
    private Date gradingDate;

    public void setGradeId(UUID uuid) {
        this.gradeId = uuid;
    }

    public int getGradeValue() {
        return gradeValue;
    }


    public void setGradeValue(int i) {
        this.gradeValue = i;
    }

    public void setGradingDate(Date date) {
        this.gradingDate = date;
    }

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }

    public void setStudent(StudentEntity student1) {
        this.studentEntity = student1;
    }

    public void setSubject(SubjectEntity math) {
        this.subjectEntity = math;
    }

    public Date getGradingDate() {
        return gradingDate;
    }

    public SubjectEntity getSubjectEntity() {
        return subjectEntity;
    }
}
