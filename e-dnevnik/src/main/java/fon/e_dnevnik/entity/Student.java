package fon.e_dnevnik.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name="student")
public class Student implements Serializable {

    @Id
    @Column(name="username")
    private String username;

    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    @Column(name="umcn")
    private String UMCN;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "studentclass", referencedColumnName = "classid")
    private Class studentClass;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Grade> grades;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Absence> absences;

    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User userStudent;

//    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    @JoinColumn(name = "username1", referencedColumnName = "username")
//    @JsonIgnore
//    private User userStudent;
}
