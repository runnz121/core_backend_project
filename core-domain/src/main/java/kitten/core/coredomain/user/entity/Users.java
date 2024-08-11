package kitten.core.coredomain.user.entity;

import jakarta.persistence.*;
import kitten.core.coredomain.model.Audit;
import kitten.core.coredomain.model.AuthRoles;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"key"}, callSuper = false)
@Entity
@Table(name = "USERS")
public class Users extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_KEY", nullable = false)
    private Long key;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthRoles authRoles;
}
