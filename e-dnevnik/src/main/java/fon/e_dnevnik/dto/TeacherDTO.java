package fon.e_dnevnik.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class TeacherDTO {

    private String username;

    @NotBlank(message = "Teacher must have a firstname.")
    private String firstname;

    @NotBlank(message = "Teacher must have a lastname.")
    private String lastname;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SubjectDTO subject;
}
