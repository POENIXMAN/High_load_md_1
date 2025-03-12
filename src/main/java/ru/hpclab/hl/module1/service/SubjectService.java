package ru.hpclab.hl.module1.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.model.Subject;
import ru.hpclab.hl.module1.repository.SubjectRepository;

import java.util.List;
import java.util.UUID;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubjectById(String id) {
        return subjectRepository.findById(UUID.fromString(id));
    }

    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public void deleteSubject(String id) {
        subjectRepository.delete(UUID.fromString(id));
    }

    public Subject updateSubject(String id, Subject subject) {
        subject.setIdentifier(UUID.fromString(id));
        return subjectRepository.put(subject);
    }
}
