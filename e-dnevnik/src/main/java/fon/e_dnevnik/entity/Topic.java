package fon.e_dnevnik.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(
        name = "topic",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_topic_subject_name", columnNames = {"subjectid", "name"})
        },
        indexes = {
                @Index(name = "idx_topic_subject", columnList = "subjectid")
        }
)
public class Topic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topicid")
    @EqualsAndHashCode.Include
    private Integer topicid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "subjectid", nullable = false)
    private Integer subjectid;
}