package fon.e_dnevnik.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "class")
public class Class implements Serializable {

    @Id
    @Column(name="classid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classId;

    @Column(name="grade")
    private int grade;

    @Column(name="number")
    private int number;

    @Column(name="classteacher")
    private String classTeacherUsername;

//    @JoinColumn(name="classteacher",referencedColumnName = "username")
//    @OneToOne(optional = true)
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    private Teacher classTeacher;
}
