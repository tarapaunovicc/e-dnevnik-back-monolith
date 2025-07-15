package fon.e_dnevnik.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import fon.e_dnevnik.entity.Class;
import fon.e_dnevnik.entity.TeachersClasses;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Collection;

@Data
public class TeacherDTO {

    private String username;

    @NotBlank(message = "Teacher must have a firstname.")
    private String firstname;

    @NotBlank(message = "Teacher must have a lastname.")
    private String lastname;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SubjectDTO subject;

    private Collection<TeachersClasses> classes;

    private UserDTO userTeacher;

}
