package ru.hpclab.hl.module1.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeDTO {


    private UUID studentId;
    private UUID subjectId;
    private int gradeValue;
    private Date gradingDate;
}