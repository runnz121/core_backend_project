package kitten.core.coredomain.image;

import jakarta.persistence.*;
import kitten.core.coredomain.model.Audit;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"key"}, callSuper = false)
@Entity
@Table(name = "IMAGE")
public class Image extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IMAGE_KEY", nullable = false)
    private Long key;

    @Column(name = "IMAGE_URL", nullable = false)
    private String imageUrl;

    @Column(name = "DELETED", nullable = false)
    @Builder.Default
    private Boolean deleted = false;
}
