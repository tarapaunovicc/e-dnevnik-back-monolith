package fon.e_dnevnik.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

import java.util.Collection;

@Data
public class StudentDTO {

    private String username;

    @NotBlank(message = "Student must have a firstname.")
    private String firstname;

    @NotBlank(message = "Student must have a lastname.")
    private String lastname;

    @NotBlank(message = "Student must have a UMNC.")
    private String UMCN;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ClassDTO studentClass;
}
