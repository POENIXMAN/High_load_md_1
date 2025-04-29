package ru.hpclab.hl.module1.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.DTO.GradeDTO;
import ru.hpclab.hl.module1.DTO.KafkaMessage;
import ru.hpclab.hl.module1.DTO.StudentDTO;
import ru.hpclab.hl.module1.DTO.SubjectDTO;

import java.util.Map;
import java.util.UUID;

// @Service
// public class KafkaConsumerService {
//     private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

//     private final StudentService studentService;
//     private final GradeService gradeService;
//     private final ObjectMapper objectMapper;
//     private final SubjectService subjectService;

//     public KafkaConsumerService(StudentService studentService, GradeService gradeService, ObjectMapper objectMapper, SubjectService subjectService) {
//         this.studentService = studentService;
//         this.gradeService = gradeService;
//         this.objectMapper = objectMapper;
//         this.subjectService = subjectService;
//     }

//     @KafkaListener(topics = "var10", groupId = "student-group", concurrency = "2")
//     public void listen(String message) {
//         try {
//             KafkaMessage kafkaMessage = objectMapper.readValue(message, KafkaMessage.class);

//             switch (kafkaMessage.getEntity().toUpperCase()) {
//                 case "STUDENT":
//                     handleStudentMessage(kafkaMessage);
//                     break;
//                 case "GRADE":
//                     handleGradeMessage(kafkaMessage);
//                     break;
//                 case "SUBJECT":
//                     handleSubjectMessage(kafkaMessage);
//                     break;
//                 default:
//                     logger.info("Ignoring message for entity: {}", kafkaMessage.getEntity());
//             }
//         } catch (Exception e) {
//             logger.error("Error processing Kafka message: {}", message, e);
//         }
//     }

//     private void handleStudentMessage(KafkaMessage kafkaMessage) throws JsonProcessingException {
//         switch (kafkaMessage.getOperation().toUpperCase()) {
//             case "POST":
//                 StudentDTO studentDTO = objectMapper.readValue(kafkaMessage.getPayload(), StudentDTO.class);
//                 StudentDTO savedStudent = studentService.saveStudent(studentDTO); 
//                 logger.info("Created student: {}", savedStudent);
//                 break;
//             case "PUT":
//                 StudentDTO updatedStudent = objectMapper.readValue(kafkaMessage.getPayload(), StudentDTO.class);
//                 studentService.updateStudent(updatedStudent.getStudentId().toString(), updatedStudent);
//                 logger.info("Updated student with ID: {}", updatedStudent.getStudentId());
//                 break;
//             case "DEL":
//                 studentService.deleteStudent(kafkaMessage.getPayload());
//                 logger.info("Deleted student with ID: {}", kafkaMessage.getPayload());
//                 break;
//             case "CLEAR":
//                 gradeService.clearAllGrades();
//                 logger.info("Cleared all Students");
//                 break;
//             default:
//                 logger.warn("Unknown operation for STUDENT: {}", kafkaMessage.getOperation());
//         }
//     }


//     private void handleGradeMessage(KafkaMessage kafkaMessage) throws JsonProcessingException {
//         switch (kafkaMessage.getOperation().toUpperCase()) {
//             case "POST":
//                 GradeDTO gradeDTO = objectMapper.readValue(kafkaMessage.getPayload(), GradeDTO.class);
//                 gradeService.saveGrade(gradeDTO);
//                 logger.info("Created grade: {}", gradeDTO);
//                 break;
//             case "DEL":
//                 gradeService.deleteGrade(kafkaMessage.getPayload());
//                 logger.info("Deleted grade with ID: {}", kafkaMessage.getPayload());
//                 break;
//             case "CLEAR":
//                 gradeService.clearAllGrades();
//                 logger.info("Cleared all grades");
//                 break;
//             case "AVG_YEAR":
//                 int year = Integer.parseInt(kafkaMessage.getPayload());
//                 Map<UUID, Double> averages = gradeService.calculateAverageGradesForYear(year);
//                 logger.info("Calculated average grades for year {}: {}", year, averages);
//                 break;
//             default:
//                 logger.warn("Unknown operation for GRADE: {}", kafkaMessage.getOperation());
//         }
//     }

//     private void handleSubjectMessage(KafkaMessage kafkaMessage) throws JsonProcessingException {
//         switch (kafkaMessage.getOperation().toUpperCase()) {
//             case "POST":
//                 SubjectDTO subjectDTO = objectMapper.readValue(kafkaMessage.getPayload(), SubjectDTO.class);
//                 subjectService.saveSubject(subjectDTO);
//                 logger.info("Created subject: {}", subjectDTO);
//                 break;
//             case "PUT":
//                 SubjectDTO updatedSubject = objectMapper.readValue(kafkaMessage.getPayload(), SubjectDTO.class);
//                 subjectService.updateSubject(updatedSubject.getSubjectId().toString(), updatedSubject);
//                 logger.info("Updated subject with ID: {}", updatedSubject.getSubjectId());
//                 break;
//             case "DEL":
//                 subjectService.deleteSubject(kafkaMessage.getPayload());
//                 logger.info("Deleted subject with ID: {}", kafkaMessage.getPayload());
//                 break;
//             case "CLEAR":
//                 subjectService.clearAllSubjects();
//                 logger.info("Cleared all subjects");
//                 break;
//             default:
//                 logger.warn("Unknown operation for SUBJECT: {}", kafkaMessage.getOperation());
//         }
//     }
// }

