package ru.hpclab.hl.module1.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hpclab.hl.module1.model.Student;
import ru.hpclab.hl.module1.repository.StudentRepository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserServiceTest.UserServiceTestConfiguration.class})
public class UserServiceTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testCreateAndGet(){
        //create
        Student user = new Student(UUID.randomUUID(), "name", "nae", "asf", "asfas");

        Student savedUser = studentService.saveUser(user);

        Assertions.assertEquals(user.getFirstName(), savedUser.getFirstName());
        Mockito.verify(studentRepository, Mockito.times(1)).save(user);

        //getAll
        List<Student> userList = studentService.getAllStudents();

        Assertions.assertEquals("name1", userList.get(0).getFirstName());
        Assertions.assertEquals("name2", userList.get(1).getFirstName());
        Mockito.verify(studentRepository, Mockito.times(1)).findAll();

    }

    @Configuration
    static class UserServiceTestConfiguration {

        @Bean
        StudentRepository studentRepository() {
            StudentRepository studentRepository1 = mock(StudentRepository.class);
            when(studentRepository1.save(any())).thenReturn(new Student(UUID.randomUUID(), "name", "nae", "asf", "asfas"));
            when(studentRepository1.findAll())
                    .thenReturn(Arrays.asList(new Student(UUID.randomUUID(), "name1", "nae1", "asf1", "asfas1"),
                            new Student(UUID.randomUUID(), "name2", "nae2", "asf2", "asfas2")));
            return studentRepository1;
        }

        @Bean
        StudentService UserService(StudentService userRepository){
            return new StudentService(studentRepository());
        }
    }

}
