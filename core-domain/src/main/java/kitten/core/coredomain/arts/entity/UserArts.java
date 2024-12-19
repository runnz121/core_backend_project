package kitten.core.coredomain.arts.entity;

import jakarta.persistence.*;
import kitten.core.coredomain.config.annotation.Description;
import kitten.core.coredomain.model.Audit;
import kitten.core.coredomain.theme.entity.Theme;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.lang.Nullable;

import java.util.Objects;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorColumn(name = "USER_ARTS")
@Table(name = "USER_ARTS")
public abstract class UserArts extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ARTS_KEY", nullable = false)
    private Long key;

    @Nullable
    @Description("현재는 1:1 관계")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "THEME_KEY", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Theme theme;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        UserArts userArts = (UserArts) o;
        return Objects.equals(key, userArts.key);
    }

    @Override
    public int hashCode() {
        return key.intValue();
    }
}
