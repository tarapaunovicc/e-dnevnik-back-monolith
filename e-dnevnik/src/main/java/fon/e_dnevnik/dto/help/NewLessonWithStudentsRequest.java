package fon.e_dnevnik.dto.help;

import fon.e_dnevnik.dto.LessonDTO;
import lombok.Data;

import java.util.List;

@Data
public class NewLessonWithStudentsRequest {
    private LessonDTO lesson;
    private List<String> students;
}