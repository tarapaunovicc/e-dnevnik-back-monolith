package fon.e_dnevnik.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Data
@Getter
@Setter
public class LessonDTO {

    private Integer lessonid;
    private Integer classid;           // iz TeachersClasses
    private String teacherUsername;   // iz TeachersClasses
    private LocalDate date;
    private int classOrdinalNumber;
    private String curriculum;

}
