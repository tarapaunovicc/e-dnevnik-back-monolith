package fon.e_dnevnik.dto;

import fon.e_dnevnik.entity.Class;
import fon.e_dnevnik.entity.Teacher;
import fon.e_dnevnik.entity.primarykey.LessonPK;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
@Data
public class LessonDTO {

    private LessonPK id;

    //@NotBlank(message = "Lesson date is a required field.")
    private LocalDate date;

    @NotBlank(message = "Class ordinal number is a required field.")
    private int classOrdinalNumber;

    private String curriculum;

    private Teacher teacher;

    private Class cl;
}
