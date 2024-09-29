package kitten.core.coredomain.user.entity;

import jakarta.persistence.*;
import kitten.core.coredomain.model.Audit;
import kitten.core.coredomain.model.AuthRoles;
import kitten.core.coredomain.parts.entity.Parts;
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

    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;

    @Column(name = "NICK_NAME", length = 100)
    private String nickName;

    @Column(name = "PHONE_NUMBER", length = 100)
    private String phoneNumber;

    @Column(name = "PROFILE_IMG_URL")
    private String profileImgUrl;

    @Column(name = "AUTH_ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthRoles authRoles;
}
