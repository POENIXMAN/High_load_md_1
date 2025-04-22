package ru.hpclab.hl.module1.service;

import org.apache.juli.logging.Log;
import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.hpclab.hl.module1.DTO.GradeDTO;
import ru.hpclab.hl.module1.Entity.GradeEntity;
import ru.hpclab.hl.module1.Entity.StudentEntity;
import ru.hpclab.hl.module1.Entity.SubjectEntity;
import ru.hpclab.hl.module1.controller.exeption.UserException;
import ru.hpclab.hl.module1.repository.GradeRepository;
import ru.hpclab.hl.module1.repository.StudentRepository;
import ru.hpclab.hl.module1.repository.SubjectRepository;

import java.net.http.HttpHeaders;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class GradeService {
    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public GradeService (
            GradeRepository gradeRepository,
            StudentRepository studentRepository,
            SubjectRepository subjectRepository,
            ModelMapper modelMapper

    ) {
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.modelMapper = modelMapper;
    }

    private GradeEntity convertToEntity(GradeDTO gradeDTO) {
        GradeEntity gradeEntity = new GradeEntity();

        gradeEntity.setGradeValue(gradeDTO.getGradeValue());
        gradeEntity.setGradingDate(gradeDTO.getGradingDate());

        StudentEntity student = studentRepository.findById(gradeDTO.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        SubjectEntity subject = subjectRepository.findById(gradeDTO.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        gradeEntity.setStudentEntity(student);
        gradeEntity.setSubjectEntity(subject);

        return gradeEntity;
    }



    public GradeDTO convertToDTO(GradeEntity gradeEntity) {
        GradeDTO gradeDTO = new GradeDTO();
        gradeDTO.setStudentId(gradeEntity.getStudentEntity().getStudentId());
        gradeDTO.setSubjectId(gradeEntity.getSubjectEntity().getSubjectId());
        gradeDTO.setGradeValue(gradeEntity.getGradeValue());
        gradeDTO.setGradingDate(gradeEntity.getGradingDate());
        return gradeDTO;
    }


    public List<GradeDTO> getAllGrades() {
        return gradeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    public GradeDTO getGradeById(String id) {
        GradeEntity entity =  gradeRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new UserException("Grade with ID " + id + " not found"));
        return convertToDTO(entity);
    }

    public GradeDTO saveGrade(GradeDTO grade) {
        GradeEntity entity = convertToEntity(grade);
        return convertToDTO( gradeRepository.save(entity));
    }

    public void deleteGrade(String id) {
        gradeRepository.deleteById(UUID.fromString(id));
    }

    public GradeDTO updateGrade(String id, GradeDTO grade) {
        GradeEntity entity = convertToEntity(grade);
        entity.setGradeId(UUID.fromString(id));
        return  convertToDTO( gradeRepository.save(entity));
    }


    public void clearAllGrades() {
        gradeRepository.deleteAll();
    }
}
