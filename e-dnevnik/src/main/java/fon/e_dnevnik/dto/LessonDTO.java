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
    private Integer classid;
    private String teacherusername;
    private LocalDate date;
    private int classOrdinalNumber;
    private String curriculum;
    private int topicid;

}
