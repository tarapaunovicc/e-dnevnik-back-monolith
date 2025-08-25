package fon.e_dnevnik.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fon.e_dnevnik.entity.primarykey.AbsencePK;
import fon.e_dnevnik.entity.primarykey.LessonPK;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "absence")
public class Absence implements Serializable {

    @EmbeddedId
    private AbsencePK id;

    @Column(name="excused")
    private boolean excused;

    @Column(name="isfinal")
    private boolean isfinal;

    @JoinColumn(name="studentusername",referencedColumnName = "username",insertable=false, updatable=false)
    @ManyToOne(optional = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Student student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "lesson_id", referencedColumnName = "lesson_id", insertable=false, updatable=false)
    private Lesson lesson;

}
