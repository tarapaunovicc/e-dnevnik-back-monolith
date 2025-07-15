package fon.e_dnevnik.dto;

import fon.e_dnevnik.entity.Lesson;
import fon.e_dnevnik.entity.Student;
import fon.e_dnevnik.entity.primarykey.AbsencePK;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
public class AbsenceDTO {

    private AbsencePK id;

    private boolean excused;

    private boolean isfinal;

    private Student student;

    private Lesson lesson;

}
