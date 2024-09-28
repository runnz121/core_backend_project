package kitten.core.coredomain.moru.entity;

import jakarta.persistence.*;
import kitten.core.coredomain.config.annotation.Description;
import kitten.core.coredomain.model.Audit;
import kitten.core.coredomain.moru.consts.MoruSide;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "MORU_USER_PART")
public class MoruUserPart extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MORU_USER_ART_KEY", nullable = false)
    private Long key;

    @Description("사용된 파츠 정보")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MORU_PARTS_KEY", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private MoruParts moruParts;

    @Description("유저가 생성한 모루 파츠 정보")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MORU_USER_ART_INFO_KEY", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private MoruUserArtInfo moruUserArtInfo;

    @Description("모루 파츠 x좌표")
    @Column(name = "COR_X")
    private Integer corX;

    @Description("모루 파츠 y좌표")
    @Column(name = "COR_Y")
    private Integer corY;

    @Description("모루 파츠 Z좌표")
    @Column(name = "COR_Z")
    @Builder.Default
    private Integer corZ = 0;

    @Description("모루 파츠 회전 각도")
    @Column(name = "ROTATION")
    @Builder.Default
    private Integer rotation = 0;

    @Description("모루 인형 정면 / 후면 적용 위치")
    @Column(name = "SIDE")
    @Enumerated(EnumType.STRING)
    private MoruSide side;
}
