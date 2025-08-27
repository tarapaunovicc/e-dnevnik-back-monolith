package fon.e_dnevnik.dto;

import fon.e_dnevnik.entity.primarykey.AbsencePK;
import lombok.Data;

@Data
public class AbsenceDTO {

    private AbsencePK id;

    private boolean excused;

    private boolean isfinal;

    private LessonDTO lesson;

    private String subjectname;
}
