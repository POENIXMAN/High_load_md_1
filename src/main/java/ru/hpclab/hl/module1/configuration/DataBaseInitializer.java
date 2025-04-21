package ru.hpclab.hl.module1.configuration;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hpclab.hl.module1.Entity.GradeEntity;
import ru.hpclab.hl.module1.Entity.StudentEntity;
import ru.hpclab.hl.module1.Entity.SubjectEntity;
import ru.hpclab.hl.module1.model.Grade;
import ru.hpclab.hl.module1.model.Student;
import ru.hpclab.hl.module1.model.Subject;
import ru.hpclab.hl.module1.repository.GradeRepository;
import ru.hpclab.hl.module1.repository.StudentRepository;
import ru.hpclab.hl.module1.repository.SubjectRepository;

import java.util.Date;
import java.util.UUID;


public class DataBaseInitializer {

    @Bean
    CommandLineRunner initDatabase(StudentRepository studentRepository,
                                   SubjectRepository subjectRepository,
                                   GradeRepository gradeRepository) {
        return args -> {
            // Populate Students
            StudentEntity student1 = new StudentEntity();
            student1.setFIO("John Doe");
            student1.setClassName("10A");
            student1.setDateOfBirth(new Date());
            studentRepository.save(student1);

            StudentEntity student2 = new StudentEntity();
            student2.setFIO("Jane Smith");
            student2.setClassName("10A");
            student2.setDateOfBirth(new Date());
            studentRepository.save(student2);

            StudentEntity student3 = new StudentEntity();
            student3.setFIO("Alice Johnson");
            student3.setClassName("10B");
            student3.setDateOfBirth(new Date());
            studentRepository.save(student3);

            // Populate Subjects
            SubjectEntity math = new SubjectEntity();
            math.setClassName("Mathematics");
            math.setRoomNumber(1);
            subjectRepository.save(math);

            SubjectEntity physics = new SubjectEntity();
            physics.setClassName("Physics");
            physics.setRoomNumber(2);
            subjectRepository.save(physics);

            // Populate Grades
            GradeEntity grade1 = new GradeEntity();
            grade1.setStudent(student1);
            grade1.setSubject(math);
            grade1.setGradeValue(85);
            grade1.setGradingDate(new Date(123, 0, 15)); // January 15, 2023
            gradeRepository.save(grade1);

            GradeEntity grade2 = new GradeEntity();
            grade2.setStudent(student2);
            grade2.setSubject(math);
            grade2.setGradeValue(90);
            grade2.setGradingDate(new Date(123, 0, 16)); // January 16, 2023
            gradeRepository.save(grade2);

            GradeEntity grade3 = new GradeEntity();
            grade3.setStudent(student3);
            grade3.setSubject(math);
            grade3.setGradeValue(78);
            grade3.setGradingDate(new Date(123, 0, 17)); // January 17, 2023
            gradeRepository.save(grade3);

            GradeEntity grade4 = new GradeEntity();
            grade4.setStudent(student1);
            grade4.setSubject(physics);
            grade4.setGradeValue(88);
            grade4.setGradingDate(new Date(123, 0, 18)); // January 18, 2023
            gradeRepository.save(grade4);

            GradeEntity grade5 = new GradeEntity();
            grade5.setStudent(student2);
            grade5.setSubject(physics);
            grade5.setGradeValue(92);
            grade5.setGradingDate(new Date(123, 0, 19)); // January 19, 2023
            gradeRepository.save(grade5);

            GradeEntity grade6 = new GradeEntity();
            grade6.setStudent(student3);
            grade6.setSubject(physics);
            grade6.setGradeValue(81);
            grade6.setGradingDate(new Date(123, 0, 20)); // January 20, 2023
            gradeRepository.save(grade6);
        };
    }
}