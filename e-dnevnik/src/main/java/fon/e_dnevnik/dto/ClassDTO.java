package fon.e_dnevnik.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClassDTO {

    private int classId;

    @NotBlank(message = "Grade is a required field.")
    private int grade;

    @NotBlank(message = "Grade number is a required field.")
    private int number;

    private String classTeacherUsername;
}
