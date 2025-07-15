package fon.e_dnevnik.entity;

import fon.e_dnevnik.entity.primarykey.TeachersClassesPK;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name="teachersclasses")
public class TeachersClasses implements Serializable {

    @EmbeddedId
    private TeachersClassesPK id;

    @JoinColumn(name="teacherusername",referencedColumnName = "username",insertable=false, updatable=false)
    @ManyToOne(optional = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Teacher teacher;

    @JoinColumn(name="classid",referencedColumnName = "classid",insertable=false, updatable=false)
    @ManyToOne(optional = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Class cl;

}
