package fon.e_dnevnik.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SubjectDTO {

    private int subjectId;

    @NotBlank(message = "Subject name is a required field.")
    private String name;
}
