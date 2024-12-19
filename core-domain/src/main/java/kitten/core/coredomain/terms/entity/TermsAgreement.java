package kitten.core.coredomain.terms.entity;

import jakarta.persistence.*;
import kitten.core.coredomain.model.Audit;
import kitten.core.coredomain.user.entity.Users;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"key"}, callSuper = false)
@Entity
@Table(name = "TERMS_AGREEMENT")
public class TermsAgreement extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TERMS_AGREEMENT_KEY", nullable = false)
    private Long key;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_KEY", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TERMS_KEY", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Terms terms;
}
