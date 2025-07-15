package fon.e_dnevnik.entity;

import fon.e_dnevnik.entity.primarykey.GradePK;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "grade")
public class Grade implements Serializable {

    @EmbeddedId
    private GradePK id;

    @Column(name="date")
    private LocalDate date;

    @Column(name="grade")
    private int grade;

    @JoinColumn(name="studentusername",referencedColumnName = "username", insertable=false, updatable=false)
    @ManyToOne(optional = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Student student;

    @JoinColumn(name="teacherusername",referencedColumnName = "username",insertable=false, updatable=false)
    @ManyToOne(optional = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Teacher teacher;

    public Grade(GradePK id) {
        this.id = id;
    }

    public Grade(String teacherUsername, String studentUsername, int gradeid){
        this.id=new GradePK(teacherUsername,studentUsername,gradeid);
    }
}
