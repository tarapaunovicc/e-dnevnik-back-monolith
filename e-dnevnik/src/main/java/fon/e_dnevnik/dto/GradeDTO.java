package fon.e_dnevnik.dto;


import fon.e_dnevnik.entity.primarykey.GradePK;
import lombok.Data;
import java.time.LocalDate;

@Data
public class GradeDTO {

    private GradePK id;

    private LocalDate date;

    private int grade;

    private TeacherDTO teacher;
}

