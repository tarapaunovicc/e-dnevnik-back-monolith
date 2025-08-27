package fon.e_dnevnik.dto;


import lombok.Data;
import java.time.LocalDate;

@Data
public class GradeDTO {

    private String studentusername;

    private String teacherusername;

    private Integer gradeid;

    private LocalDate date;

    private int grade;

    private String subjectName;
}

