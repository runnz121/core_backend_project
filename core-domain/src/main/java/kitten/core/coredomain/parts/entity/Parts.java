package kitten.core.coredomain.parts.entity;

import jakarta.persistence.*;
import kitten.core.coredomain.config.annotation.Description;
import kitten.core.coredomain.model.Audit;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorColumn(name = "PARTS")
@Table(name = "PARTS")
public abstract class Parts extends Audit {

    @Id
    @Description("모루 파츠는 키가 부모 / 자식도 각각 생성되는 구조")
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
