package ru.hpclab.hl.module1.repository;

import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import ru.hpclab.hl.module1.controller.exeption.UserException;
import ru.hpclab.hl.module1.model.Student;
import ru.hpclab.hl.module1.model.Subject;

import java.util.*;

import static java.lang.String.format;

@Repository
public class SubjectRepository {


    public static final String CLASS_NOT_FOUND_MSG = "Class with ID %s not found";
    public static final String CLASS_EXISTS_MSG = "Class with ID %s is already exists";
    private final Map<UUID, Subject> subjectMap = new HashMap<>();


    public Subject findById(UUID id) {
        final var subject = subjectMap.get(id);
        if (subject == null) {
            throw new UserException(format(CLASS_NOT_FOUND_MSG, id));
        }
        return subject;
    }

    public Subject save(Subject subject) {
        if (ObjectUtils.isEmpty(subject.getIdentifier())) {
            subject.setIdentifier(UUID.randomUUID());
        }

        final var subjectData = subjectMap.get(subject.getIdentifier());
        if (subjectData != null) {
            throw new UserException(format(CLASS_EXISTS_MSG, subject.getIdentifier()));
        }

        subjectMap.put(subject.getIdentifier(), subject);

        return subject;
    }

    public Subject put(Subject subject ){
        final var subjectData = subjectMap.get(subject.getIdentifier());
        if (subjectData == null) {
            throw new UserException(format(CLASS_NOT_FOUND_MSG, subject.getIdentifier()));
        }
        final var removed = subjectMap.remove(subject.getIdentifier());
        if (removed != null) {
            subjectMap.put(subject.getIdentifier(), subject);
        } else {
            throw new UserException(format(CLASS_NOT_FOUND_MSG, subject.getIdentifier()));
        }

        return subject;
    }

    public void delete(UUID id) {
        final var removed = subjectMap.remove(id);
        if (removed == null) {
            throw new UserException(format(CLASS_NOT_FOUND_MSG, id));
        }
    }

    public void clear(){
        subjectMap.clear();
    }

    public List<Subject> findAll() {
        return new ArrayList<>(subjectMap.values());
    }
}
