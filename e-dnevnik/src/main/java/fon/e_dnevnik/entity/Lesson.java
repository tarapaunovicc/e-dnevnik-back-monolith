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

    @EmbeddedId
    private LessonPK id;

    @Column(name="date")
    private LocalDate date;

    @Column(name="classordinalnumber")
    private int classOrdinalNumber;

    @Column(name="curriculum")
    private String curriculum;

    @JoinColumn(name="username",referencedColumnName = "username",insertable=false, updatable=false)
    @ManyToOne(optional = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Teacher teacher;

    @JoinColumn(name="classid",referencedColumnName = "classid",insertable=false, updatable=false)
    @ManyToOne(optional = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Class cl;
    public Lesson(LessonPK id) {
        this.id = id;
    }

    public Lesson(Integer classId,String username, int lessonid){
        this.id=new LessonPK(classId,username,lessonid);
    }
}
