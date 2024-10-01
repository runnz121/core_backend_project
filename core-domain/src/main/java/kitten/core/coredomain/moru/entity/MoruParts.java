package kitten.core.coredomain.moru.entity;

import jakarta.persistence.*;
import kitten.core.coredomain.config.annotation.Description;
import kitten.core.coredomain.moru.consts.MoruStatus;
import kitten.core.coredomain.parts.entity.Parts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@DynamicUpdate
@SQLRestriction("deleted = false")
@DiscriminatorValue("MORU_PARTS")
@Table(name = "MORU_PARTS")
@EntityListeners(AuditingEntityListener.class)
public class MoruParts extends Parts {

    @Description("모루 파츠 부모키")
    @Column(name = "PARENT_KEY")
    private Long parentKey;

    @Description("모루 대표 파츠 여부")
    @Column(name = "REPRESENTATIVE")
    private Boolean representative;

    @Description("모루 승인 상태 [APPROVE : 관리자 승인, PENDING : 관리자 승인 대기]")
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private MoruStatus status;

    @Description("모루 파츠 이름")
    @Column(name = "NAME")
    private String name;

    @Description("모루 파츠 이미지 url")
    @Column(name = "IMG_URL")
    private String imageUrl;

    @Description("가로 길이 (mm 단위)")
    @Column(name = "WIDTH")
    private Integer width;

    @Description("세로 길이 (mm 단위)")
    @Column(name = "HEIGHT")
    private Integer height;

    @Description("모루 파츠 컬러 색상 코드")
    @Column(name = "COLOR_HEX_CODE")
    private String colorHexCode;

    @Description("모루 파츠 구매 정보")
    @Column(name = "PURCHASE_INFO")
//    @Type(JsonStringType.class)
    private String purchaseInfo;

    @Description("삭제처리 여부")
    @Builder.Default
    private Boolean deleted = false;

    public List<String> getPurchaseInfos() {
        return List.of(purchaseInfo.split(","));
    }

    public MoruParts deleteParts() {
        this.deleted = true;
        return this;
    }
}
