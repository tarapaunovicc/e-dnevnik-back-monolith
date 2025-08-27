package fon.e_dnevnik.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

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

    @Column(name = "studentclass", nullable = false)
    private Integer studentClass;

//    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    @JoinColumn(name = "studentclass", referencedColumnName = "classid")
//    private Class studentClass;

}
