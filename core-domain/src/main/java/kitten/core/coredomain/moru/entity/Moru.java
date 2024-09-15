package kitten.core.coredomain.moru.entity;

import jakarta.persistence.*;
import kitten.core.coredomain.config.annotation.Description;
import kitten.core.coredomain.model.Audit;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"key"}, callSuper = false)
@Entity
@Table(name = "MORU")
public class Moru extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MORU_KEY", nullable = false)
    private Long key;

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
