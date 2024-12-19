package kitten.core.coredomain.moru.entity;

import jakarta.persistence.*;
import kitten.core.coredomain.config.annotation.Description;
import kitten.core.coredomain.user.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Description("모루 인형 저장 정보")
@Table(name = "MORU_USER_ART_INFO")
public class MoruUserArtInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MORU_USER_ART_INFO_KEY", nullable = false)
    private Long key;

    @Description("인형 저장 정보 유저")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_KEY", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Users users;

    @Description("사용된 모루 인형")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MORU_KEY", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Moru moru;

    @Description("모루 인형 색상 코드")
    @Column(name = "MORU_COLOR_HEX_CODE")
    private String moruColorHexCode;

    @Description("공간 가로 길이")
    @Column(name = "WIDTH")
    private Integer width;

    @Description("공간 세로 길이")
    @Column(name = "HEIGHT")
    private Integer height;

    @Description("작품 앞 이미지")
    @Column(name = "FRONT_IMG_URL")
    private String frontImgUrl;

    @Description("작품 뒤 이미지")
    @Column(name = "BACK_IMG_URL")
    private String backImgUrl;

    @Description("삭제처리 여부")
    @Builder.Default
    @Column(name = "DELETED", nullable = false)
    private Boolean deleted = false;

    public MoruUserArtInfo deleteArtInfo() {
        this.deleted = true;
        return this;
    }

    public MoruUserArtInfo updateAll(Moru moru,
                                     String moruColorHexCode,
                                     Integer width,
                                     Integer height,
                                     String frontImgUrl,
                                     String backImgUrl) {
        this.moruColorHexCode = moruColorHexCode;
        this.moru = moru;
        this.width = width;
        this.height = height;
        this.frontImgUrl = frontImgUrl;
        this.backImgUrl = backImgUrl;
        return this;
    }
}
