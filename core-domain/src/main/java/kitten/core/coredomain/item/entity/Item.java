package kitten.core.coredomain.item.entity;

import jakarta.persistence.*;
import kitten.core.coredomain.model.Audit;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorColumn(name = "ITEM")
@Table(name = "ITEM")
public abstract class Item extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_KEY", nullable = false)
    private Long key;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        Item item = (Item) o;
        return Objects.equals(key, item.key);
    }

    @Override
    public int hashCode() {
        return key.intValue();
    }
}
