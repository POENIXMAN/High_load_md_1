package ru.hpclab.hl.module1.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.DTO.StudentDTO;
import ru.hpclab.hl.module1.Entity.StudentEntity;
import ru.hpclab.hl.module1.controller.exeption.UserException;
import ru.hpclab.hl.module1.model.Student;
import ru.hpclab.hl.module1.repository.StudentRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;


    private final ModelMapper modelMapper;

    private StudentDTO createStudentDTO(StudentEntity student) {
        return modelMapper.map(student, StudentDTO.class);
    }

    private StudentEntity createStudentEntity(StudentDTO studentDTO) {
        return modelMapper.map(studentDTO, StudentEntity.class);
    }

    public StudentService(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream().map(
                this::createStudentDTO
        ).collect(Collectors.toList());
    }

    public StudentDTO getStudentById(String id) {
        StudentEntity student = studentRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new UserException("Student with ID " + id + " not found"));
        return createStudentDTO(student);
    }

    public StudentDTO saveStudent(StudentDTO student) {
        StudentEntity savedStudent = studentRepository.save(createStudentEntity(student));
        return createStudentDTO(savedStudent);
    }

    public void deleteStudent(String id) {
        studentRepository.deleteById(UUID.fromString(id));
    }

    public StudentDTO updateStudent(String id, StudentDTO updatedStudent) {
        UUID uuid = UUID.fromString(id);
        StudentEntity existingStudent = studentRepository.findById(uuid)
                .orElseThrow(() -> new UserException("Student with ID " + id + " not found"));

        existingStudent.setFIO(updatedStudent.getFIO());
        existingStudent.setClassName(updatedStudent.getClassName());
        existingStudent.setDateOfBirth(updatedStudent.getDateOfBirth());

        return createStudentDTO( studentRepository.save(existingStudent));
    }

    public void deleteAllStudents() {
        studentRepository.deleteAll();
    }
}
