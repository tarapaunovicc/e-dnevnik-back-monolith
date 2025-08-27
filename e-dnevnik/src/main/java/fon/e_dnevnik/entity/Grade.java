package fon.e_dnevnik.entity;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gradeid;

    @Column(nullable = false)
    private String studentusername;

    @Column(nullable = false)
    private String teacherusername;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private int grade;


//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "teacherusername", referencedColumnName = "username",
//            insertable = false, updatable = false)
//    @ToString.Exclude @EqualsAndHashCode.Exclude
//    private Teacher teacher;
}
