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
@Table(name="teacher")
public class Teacher implements Serializable {

    @Id
    @Column(name="username")
    private String username;

    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "subject", referencedColumnName = "subjectid")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Subject subject;

//    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    @JoinColumn(name = "username1", referencedColumnName = "username")
//    @JsonIgnore
//    private User userTeacher;
    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User userTeacher;
}
