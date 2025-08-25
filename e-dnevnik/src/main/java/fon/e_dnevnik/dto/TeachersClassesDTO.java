package fon.e_dnevnik.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import fon.e_dnevnik.entity.Class;
import fon.e_dnevnik.entity.Teacher;
import fon.e_dnevnik.entity.primarykey.TeachersClassesPK;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
public class TeachersClassesDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer classid;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String teacherusername;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ClassDTO cl;
}
