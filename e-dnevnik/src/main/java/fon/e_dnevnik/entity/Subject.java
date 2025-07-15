package fon.e_dnevnik.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "subject")
public class Subject implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="subjectid")
    private int subjectId;

    @Column(name="name")
    private String name;
}
