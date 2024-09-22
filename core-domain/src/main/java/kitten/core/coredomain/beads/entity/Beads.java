package kitten.core.coredomain.beads.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import kitten.core.coredomain.item.entity.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Builder
@DiscriminatorValue("BEADS")
@Table(name = "BEADS")
public class Beads extends Item {
}
