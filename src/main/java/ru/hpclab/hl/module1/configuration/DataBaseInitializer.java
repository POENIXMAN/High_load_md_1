package ru.hpclab.hl.module1.configuration;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hpclab.hl.module1.model.Grade;
import ru.hpclab.hl.module1.model.Student;
import ru.hpclab.hl.module1.model.Subject;
import ru.hpclab.hl.module1.repository.GradeRepository;
import ru.hpclab.hl.module1.repository.StudentRepository;
import ru.hpclab.hl.module1.repository.SubjectRepository;

import java.util.Date;
import java.util.UUID;

@Configuration
public class DataBaseInitializer {

    @Bean
    CommandLineRunner initDatabase(StudentRepository studentRepository,
                                   SubjectRepository subjectRepository,
                                   GradeRepository gradeRepository) {
        return args -> {
            // Populate Students
            Student student1 = new Student();
            student1.setFIO("John Doe");
            student1.setClassName("10A");
            student1.setDateOfBirth(new Date());
            studentRepository.save(student1);

            Student student2 = new Student();
            student2.setFIO("Jane Smith");
            student2.setClassName("10A");
            student2.setDateOfBirth(new Date());
            studentRepository.save(student2);

            Student student3 = new Student();
            student3.setFIO("Alice Johnson");
            student3.setClassName("10B");
            student3.setDateOfBirth(new Date());
            studentRepository.save(student3);

            // Populate Subjects
            Subject math = new Subject();
            math.setClassName("Mathematics");
            math.setRoomNumber(1);
            subjectRepository.save(math);

            Subject physics = new Subject();
            physics.setClassName("Physics");
            physics.setRoomNumber(2);
            subjectRepository.save(physics);

            // Populate Grades
            Grade grade1 = new Grade();
            grade1.setStudent(student1);
            grade1.setaSubject(math);
            grade1.setGradeValue(85);
            grade1.setGradingDate(new Date(123, 0, 15)); // January 15, 2023
            gradeRepository.save(grade1);

            Grade grade2 = new Grade();
            grade2.setStudent(student2);
            grade2.setaSubject(math);
            grade2.setGradeValue(90);
            grade2.setGradingDate(new Date(123, 0, 16)); // January 16, 2023
            gradeRepository.save(grade2);

            Grade grade3 = new Grade();
            grade3.setStudent(student3);
            grade3.setaSubject(math);
            grade3.setGradeValue(78);
            grade3.setGradingDate(new Date(123, 0, 17)); // January 17, 2023
            gradeRepository.save(grade3);

            Grade grade4 = new Grade();
            grade4.setStudent(student1);
            grade4.setaSubject(physics);
            grade4.setGradeValue(88);
            grade4.setGradingDate(new Date(123, 0, 18)); // January 18, 2023
            gradeRepository.save(grade4);

            Grade grade5 = new Grade();
            grade5.setStudent(student2);
            grade5.setaSubject(physics);
            grade5.setGradeValue(92);
            grade5.setGradingDate(new Date(123, 0, 19)); // January 19, 2023
            gradeRepository.save(grade5);

            Grade grade6 = new Grade();
            grade6.setStudent(student3);
            grade6.setaSubject(physics);
            grade6.setGradeValue(81);
            grade6.setGradingDate(new Date(123, 0, 20)); // January 20, 2023
            gradeRepository.save(grade6);
        };
    }
}