package ru.hpclab.hl.module1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import ru.hpclab.hl.module1.Entity.StudentEntity;
import ru.hpclab.hl.module1.controller.exeption.UserException;
import ru.hpclab.hl.module1.model.Student;

import java.util.*;

import static java.lang.String.format;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {

}
