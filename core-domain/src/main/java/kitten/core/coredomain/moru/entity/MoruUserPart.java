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
    @Column(name = "MORU_USER_PART_KEY", nullable = false)
    private Long key;

    @Description("사용된 파츠 정보")
    @OneToOne(fetch = FetchType.LAZY, optional = false)
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

    @Description("유저가 커스텀한 파츠 가로길이")
    @Column(name = "CUSTOM_WIDTH")
    private Integer customWidth;

    @Description("모루 파츠 y좌표")
    @Column(name = "CUSTOM_HEIGHT")
    private Integer customHeight;

    @Description("삭제처리 여부")
    @Builder.Default
    @Column(name = "DELETED", nullable = false)
    private Boolean deleted = false;

    public MoruUserPart deleteUserPart() {
        this.deleted = true;
        return this;
    }

    public MoruUserPart updateAll(MoruParts moruParts,
                                  MoruUserArtInfo moruUserArtInfo,
                                  Integer corX,
                                  Integer corY,
                                  Integer corZ,
                                  Integer rotation,
                                  MoruSide side,
                                  Integer customWidth,
                                  Integer customHeight) {
        this.moruParts = moruParts;
        this.moruUserArtInfo = moruUserArtInfo;
        this.corX = corX;
        this.corY = corY;
        this.corZ = corZ;
        this.rotation = rotation;
        this.side = side;
        this.customWidth = customWidth;
        this.customHeight = customHeight;
        return this;
    }
}
