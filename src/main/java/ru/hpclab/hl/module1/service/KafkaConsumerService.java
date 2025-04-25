package ru.hpclab.hl.module1.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.DTO.KafkaMessage;
import ru.hpclab.hl.module1.DTO.StudentDTO;

@Service
public class KafkaConsumerService {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    private final StudentService studentService;
    private final ObjectMapper objectMapper;

    public KafkaConsumerService(StudentService studentService, ObjectMapper objectMapper) {
        this.studentService = studentService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "var10", groupId = "student-group", concurrency = "1")
    public void listen(String message) {
        try {
            KafkaMessage kafkaMessage = objectMapper.readValue(message, KafkaMessage.class);

            if (!"STUDENT".equalsIgnoreCase(kafkaMessage.getEntity())) {
                logger.info("Ignoring message for entity: {}", kafkaMessage.getEntity());
                return;
            }

            switch (kafkaMessage.getOperation().toUpperCase()) {
                case "POST":
                    StudentDTO studentDTO = objectMapper.readValue(kafkaMessage.getPayload(), StudentDTO.class);
                    studentService.saveStudent(studentDTO);
                    logger.info("Created student: {}", studentDTO);
                    break;
                case "PUT":
                    StudentDTO updatedStudent = objectMapper.readValue(kafkaMessage.getPayload(), StudentDTO.class);
                    studentService.updateStudent(updatedStudent.getStudentId().toString(), updatedStudent);
                    logger.info("Updated student with ID: {}", updatedStudent.getStudentId());
                    break;
                case "DEL":
                    studentService.deleteStudent(kafkaMessage.getPayload()); // payload is the ID
                    logger.info("Deleted student with ID: {}", kafkaMessage.getPayload());
                    break;
                default:
                    logger.warn("Unknown operation: {}", kafkaMessage.getOperation());
            }
        } catch (Exception e) {
            logger.error("Error processing Kafka message: {}", message, e);
        }
    }
}

