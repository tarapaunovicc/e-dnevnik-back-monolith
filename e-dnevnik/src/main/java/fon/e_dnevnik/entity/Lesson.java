package fon.e_dnevnik.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="lesson")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lesson implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    private Integer lessonid;

    @Column(name = "classid", nullable = false)
    private Integer classid;

    @Column(name = "username", nullable=false)
    private String teacherusername;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "classordinalnumber", nullable = false)
    private int classOrdinalNumber;

    @Column(name = "curriculum")
    private String curriculum;

}
