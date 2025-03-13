package ru.hpclab.hl.module1.DTO;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {
    private String className;
    private String teacherName;
    private int roomNumber;
}
