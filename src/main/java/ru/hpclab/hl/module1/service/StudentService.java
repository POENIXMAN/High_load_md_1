package ru.hpclab.hl.module1.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
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

    public Student getUserById(String id) {
        return studentRepository.findById(UUID.fromString(id));
    }

    public Student saveUser(Student student) {
        return studentRepository.save(student);
    }

    public void deleteUser(String id) {
        studentRepository.delete(UUID.fromString(id));
    }

    public Student updateUser(String id, Student user) {
        user.setIdentifier(UUID.fromString(id));
        return studentRepository.put(user);
    }
}
