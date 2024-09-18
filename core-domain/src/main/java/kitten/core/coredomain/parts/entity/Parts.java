package kitten.core.coredomain.parts.entity;

import jakarta.persistence.*;
import kitten.core.coredomain.model.Audit;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Getter
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorColumn(name = "PARTS")
@Table(name = "PARTS")
public abstract class Parts extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PARTS_KEY", nullable = false)
    private Long key;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        Parts parts = (Parts) o;
        return Objects.equals(key, parts.key);
    }

    @Override
    public int hashCode() {
        return key.intValue();
    }
}
