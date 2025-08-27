package fon.e_dnevnik.dto.help;

import fon.e_dnevnik.dto.AbsenceDTO;
import fon.e_dnevnik.dto.LessonDTO;
import lombok.Data;

import java.util.List;

@Data
public class NewLessonWithAbsencesResponse {
    private LessonDTO lesson;
    private List<AbsenceDTO> absences;
}