package ru.hpclab.hl.module1.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.controller.exeption.UserException;
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
        return subjectRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new UserException("Subject with ID " + id + " not found"));
    }

    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }
    public void deleteSubject(String id) {
        subjectRepository.deleteById(UUID.fromString(id));
    }
    public Subject updateSubject(String id, Subject updatedSubject) {
        UUID uuid = UUID.fromString(id);
        Subject existingSubject = subjectRepository.findById(uuid)
                .orElseThrow(() -> new UserException("Subject with ID " + id + " not found"));

        // Update fields
        existingSubject.setClassName(updatedSubject.getClassName());
        existingSubject.setRoomNumber(updatedSubject.getRoomNumber());
        existingSubject.setRoomNumber(updatedSubject.getRoomNumber());

        return subjectRepository.save(existingSubject);
    }
}
