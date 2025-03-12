package ru.hpclab.hl.module1.repository;

import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import ru.hpclab.hl.module1.controller.exeption.UserException;
import ru.hpclab.hl.module1.model.Student;

import java.util.*;

import static java.lang.String.format;

@Repository
public class StudentRepository {

    public static final String STUDENT_NOT_FOUND_MSG = "Student with ID %s not found";
    public static final String STUDENT_EXISTS_MSG = "Student with ID %s is already exists";

    private final Map<UUID, Student> students = new HashMap<>();

    public Student findById(UUID id) {
        final var user = students.get(id);
        if (user == null) {
            throw new UserException(format(STUDENT_NOT_FOUND_MSG, id));
        }
        return user;
    }

    public Student save(Student student) {
        if (ObjectUtils.isEmpty(student.getIdentifier())) {
            student.setIdentifier(UUID.randomUUID());
        }

        final var studentData = students.get(student.getIdentifier());
        if (studentData != null) {
            throw new UserException(format(STUDENT_EXISTS_MSG, student.getIdentifier()));
        }

        students.put(student.getIdentifier(), student);

        return student;
    }

    public Student put(Student student) {
        final var studentData = students.get(student.getIdentifier());
        if (studentData == null) {
            throw new UserException(format(STUDENT_NOT_FOUND_MSG, student.getIdentifier()));
        }
        final var removed = students.remove(student.getIdentifier());
        if (removed != null) {
            students.put(student.getIdentifier(), student);
        } else {
            throw new UserException(format(STUDENT_NOT_FOUND_MSG, student.getIdentifier()));
        }

        return student;
    }

    public void delete(UUID id) {
        final var removed = students.remove(id);
        if (removed == null) {
            throw new UserException(format(STUDENT_NOT_FOUND_MSG, id));
        }
    }

    public void clear(){
        students.clear();
    }

    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }
}
