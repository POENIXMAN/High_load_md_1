package ru.hpclab.hl.module1.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private UUID studentId;
    private String FIO;
    private String className;
    private Date dateOfBirth;
}