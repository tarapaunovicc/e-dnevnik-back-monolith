package fon.e_dnevnik.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClassDTO {

    private int classId;

    @NotBlank(message = "Grade is a required field.")
    private int grade;

    @NotBlank(message = "Grade number is a required field.")
    private int number;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TeacherDTO classTeacher;
}
