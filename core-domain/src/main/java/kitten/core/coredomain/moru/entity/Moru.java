package kitten.core.coredomain.moru.entity;

import jakarta.persistence.*;
import kitten.core.coredomain.config.annotation.Description;
import kitten.core.coredomain.item.entity.Item;
import kitten.core.coredomain.theme.entity.Theme;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@DiscriminatorValue("MORU")
@Table(name = "MORU")
public class Moru extends Item {

    @Description("현재는 1:1 관계")
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "THEME_KEY", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Theme theme;

    @Description("모루 이름")
    @Column(name = "NAME")
    private String name;

    @Description("가로 길이 (mm 단위)")
    @Column(name = "WIDTH")
    private Integer width;

    @Description("세로 길이 (mm 단위)")
    @Column(name = "HEIGHT")
    private Integer height;

    @Description("정면 이미지 url")
    @Column(name = "FRONT_IMG_URL")
    private String frontImageUrl;

    @Description("후면 이미지 url")
    @Column(name = "BACK_IMG_URL")
    private String backImageUrl;
}
