package fon.e_dnevnik.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import fon.e_dnevnik.entity.Student;
import fon.e_dnevnik.entity.Teacher;
import fon.e_dnevnik.entity.primarykey.GradePK;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@Data
public class GradeDTO {

    private GradePK id;

    private LocalDate date;

    private int grade;

    private TeacherDTO teacher;
}

