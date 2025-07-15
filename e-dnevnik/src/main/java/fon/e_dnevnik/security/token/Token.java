package fon.e_dnevnik.security.token;

import fon.e_dnevnik.entity.User;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name="token")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name="token",unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    @Column(name="type")
    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;

    public boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user",referencedColumnName = "username")
    public User user;
}