package kitten.core.coredomain.moru.entity;

import jakarta.persistence.*;
import kitten.core.coredomain.config.annotation.Description;
import kitten.core.coredomain.model.Audit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 현재는 사용하지 않는 엔티티
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "MORU_SPACE")
public class MoruSpace extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MORU_SPACE_KEY", nullable = false)
    private Long key;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MORU_KEY", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Moru moru;

    @Description("공간 가로 길이")
    @Column(name = "WIDTH")
    private Integer width;

    @Description("공간 세로 길이")
    @Column(name = "HEIGHT")
    private Integer height;
}
