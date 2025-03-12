package ru.hpclab.hl.module1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.hpclab.hl.module1.Application;
import ru.hpclab.hl.module1.model.Student;
import ru.hpclab.hl.module1.repository.StudentRepository;


import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class StudentControllerTest {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    public void init() {
        studentRepository.clear();
    }

    @Test
    public void get_should_returnUser_when_userExists() throws Exception {
        Student user = studentRepository.save(new Student(UUID.randomUUID(), "name", "afsdf", "asfas", "bio"));
        String expectedJson = objectMapper.writeValueAsString(user);

        mvc.perform(get("/users/" + user.getIdentifier()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }
}
