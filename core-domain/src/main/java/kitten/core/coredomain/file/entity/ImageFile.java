package kitten.core.coredomain.file.entity;

import jakarta.persistence.*;
import kitten.core.coredomain.model.Audit;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"key"}, callSuper = false)
@Entity
@Table(name = "IMAGE_FILE")
public class ImageFile extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IMAGE_FILE_KEY", nullable = false)
    private Long key;

    @Column(name = "IMAGE_URL", nullable = false, length = 100)
    private String imageUrl;

    @Column(name = "IMAGE_ID", nullable = false, length = 100)
    private String imageId;
}
