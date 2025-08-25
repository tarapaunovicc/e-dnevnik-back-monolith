package fon.e_dnevnik.entity;

import fon.e_dnevnik.entity.primarykey.LessonPK;
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

    @ManyToOne(optional = false)
    @JoinColumns({
            @JoinColumn(name = "classid", referencedColumnName = "classid"),
            @JoinColumn(name = "username", referencedColumnName = "teacherusername")
    })
    private TeachersClasses teachersClasses;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "classordinalnumber", nullable = false)
    private int classOrdinalNumber;

    @Column(name = "curriculum")
    private String curriculum;

}
