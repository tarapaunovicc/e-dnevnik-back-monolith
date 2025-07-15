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
    private Student student;

    @ManyToOne(optional = false)
    @JoinColumns({
            @JoinColumn(name = "classid", referencedColumnName = "classid",insertable=false, updatable=false),
            @JoinColumn(name = "teacherusername", referencedColumnName = "username",insertable=false, updatable=false),
            @JoinColumn(name="lessonid", referencedColumnName = "lessonid",insertable=false, updatable=false)
    })
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Lesson lesson;

    public Absence(AbsencePK id) {
        this.id = id;
    }
    public Absence(String stusername, int id,String tcusername, int lessonid){
        this.id=new AbsencePK(stusername,id,tcusername,lessonid);
    }
}
