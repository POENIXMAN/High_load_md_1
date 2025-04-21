package ru.hpclab.hl.module1.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.DTO.StudentDTO;
import ru.hpclab.hl.module1.DTO.SubjectDTO;
import ru.hpclab.hl.module1.Entity.StudentEntity;
import ru.hpclab.hl.module1.Entity.SubjectEntity;
import ru.hpclab.hl.module1.controller.exeption.UserException;
import ru.hpclab.hl.module1.model.Subject;
import ru.hpclab.hl.module1.repository.SubjectRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    private ModelMapper modelMapper;

    private SubjectDTO createSubjectDTO(SubjectEntity subject) {
        return modelMapper.map(subject, SubjectDTO.class);
    }

    private SubjectEntity createSubjectEntity(SubjectDTO subject) {
        return modelMapper.map(subject, SubjectEntity.class);
    }

    public SubjectService(SubjectRepository subjectRepository, ModelMapper modelMapper) {
        this.subjectRepository = subjectRepository;
        this.modelMapper = modelMapper;
    }

    public List<SubjectDTO> getAllSubjects() {
        List<SubjectEntity> subjectEntities = subjectRepository.findAll();
        return subjectEntities.stream()
                .map(this::createSubjectDTO)
                .collect(Collectors.toList());
    }

    public SubjectDTO getSubjectById(String id) {
        SubjectEntity subjectEntity =  subjectRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new UserException("Subject with ID " + id + " not found"));
        return createSubjectDTO(subjectEntity);
    }

    public SubjectDTO saveSubject(SubjectDTO subject) {
        SubjectEntity subjectEntity = createSubjectEntity(subject);
        SubjectEntity savedEntity = subjectRepository.save(subjectEntity);
        return createSubjectDTO(savedEntity);
    }
    public void deleteSubject(String id) {
        subjectRepository.deleteById(UUID.fromString(id));
    }
    public SubjectDTO updateSubject(String id, SubjectDTO updatedSubject) {
        UUID uuid = UUID.fromString(id);
        SubjectEntity existingSubject = subjectRepository.findById(uuid)
                .orElseThrow(() -> new UserException("Subject with ID " + id + " not found"));

        // Update fields
        existingSubject.setClassName(updatedSubject.getClassName());
        existingSubject.setRoomNumber(updatedSubject.getRoomNumber());
        existingSubject.setTeacherName(updatedSubject.getTeacherName());

        SubjectEntity updatedEntity = subjectRepository.save(existingSubject);

        return createSubjectDTO(updatedEntity);
    }

    public void clearAllSubjects() {
        subjectRepository.deleteAll();
    }
}
