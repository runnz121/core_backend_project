package kitten.core.coredomain.gemStitch.entity;

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
@DiscriminatorValue("GEM_STITCH")
@Table(name = "GEM_STITCH")
public class GemStitch extends Item {
}
