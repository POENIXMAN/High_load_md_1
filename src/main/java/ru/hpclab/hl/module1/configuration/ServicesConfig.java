package ru.hpclab.hl.module1.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hpclab.hl.module1.model.Student;
import ru.hpclab.hl.module1.repository.StudentRepository;
import ru.hpclab.hl.module1.service.StatisticsService;
import ru.hpclab.hl.module1.service.StudentService;

import java.util.Date;
import java.util.UUID;

@Configuration
public class ServicesConfig {

//    @Bean
//    StudentService studentService(StudentRepository studentRepository) {
//        StudentService studentService = new StudentService(studentRepository);
//        for (int i = 0; i < 5; i++) {
//            studentRepository.save(new Student(UUID.randomUUID(), "Ahmad", "John", "nasser", "Math"));
//        }
//        return studentService;
//    }
//
//    @Bean
//    @ConditionalOnProperty(prefix = "statistics", name = "service", havingValue = "console2000")
//    StatisticsService statisticsService2000(StudentService studentService){
//        return new StatisticsService(2000, studentService);
//    }
//
//    @Bean
//    @ConditionalOnProperty(prefix = "statistics", name = "service", havingValue = "console1000")
//    StatisticsService statisticsService1000(StudentService studentService){
//        return new StatisticsService(1000, studentService);
//    }
}
