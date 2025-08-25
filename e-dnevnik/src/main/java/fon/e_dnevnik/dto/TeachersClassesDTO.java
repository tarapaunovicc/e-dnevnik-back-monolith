package fon.e_dnevnik.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class TeachersClassesDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer classid;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String teacherusername;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ClassDTO cl;
}
