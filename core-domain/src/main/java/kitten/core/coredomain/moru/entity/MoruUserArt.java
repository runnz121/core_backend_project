package kitten.core.coredomain.moru.entity;

import jakarta.persistence.*;
import kitten.core.coredomain.config.annotation.Description;
import kitten.core.coredomain.model.Audit;
import kitten.core.coredomain.moru.consts.MoruSide;
import kitten.core.coredomain.user.entity.Users;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"key"}, callSuper = false)
@Entity
@Table(name = "MORU_USER_ART")
public class MoruUserArt extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MORU_USER_ART_KEY", nullable = false)
    private Long key;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_KEY", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MORU_KEY", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Moru moru;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MORU_PARTS_KEY", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private MoruParts moruParts;

    @Description("모루 인형 색상 코드")
    @Column(name = "MORU_COLOR_HEX_CODE")
    private String moruColorHexCode;

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

    @Description("모루 인형 정면 / 후면 적용 위치")
    @Column(name = "SIDE")
    @Enumerated(EnumType.STRING)
    private MoruSide side;
}
