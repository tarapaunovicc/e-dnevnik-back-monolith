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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GradeType type;
}
