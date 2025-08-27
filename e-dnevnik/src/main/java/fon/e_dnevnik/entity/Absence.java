package fon.e_dnevnik.entity;

import fon.e_dnevnik.entity.primarykey.AbsencePK;
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

}
