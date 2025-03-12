package ru.hpclab.hl.module1.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.controller.exeption.UserException;
import ru.hpclab.hl.module1.model.Student;
import ru.hpclab.hl.module1.repository.StudentRepository;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(String id) {
        return studentRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new UserException("Student with ID " + id + " not found"));
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(String id) {
        studentRepository.deleteById(UUID.fromString(id));
    }
    public Student updateStudent(String id, Student updatedStudent) {
        UUID uuid = UUID.fromString(id);
        Student existingStudent = studentRepository.findById(uuid)
                .orElseThrow(() -> new UserException("Student with ID " + id + " not found"));

        existingStudent.setFIO(updatedStudent.getFIO());
        existingStudent.setClassName(updatedStudent.getClassName());
        existingStudent.setDateOfBirth(updatedStudent.getDateOfBirth());

        return studentRepository.save(existingStudent);
    }

    public void deleteAllStudents() {
        studentRepository.deleteAll();
    }
}
