package fon.e_dnevnik.entity.primarykey;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Basic;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonPK implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Basic(optional = false)
    private Integer classid;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Basic(optional = false)
    private String username;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    private Integer lessonid;

}
