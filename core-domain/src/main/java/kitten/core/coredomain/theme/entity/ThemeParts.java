package kitten.core.coredomain.theme.entity;

import jakarta.persistence.*;
import kitten.core.coredomain.config.annotation.Description;
import kitten.core.coredomain.model.Audit;
import kitten.core.coredomain.parts.entity.Parts;
import kitten.core.coredomain.theme.consts.ThemePosition;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLRestriction("deleted = false")
@EqualsAndHashCode(of = {"key"}, callSuper = false)
@Entity
@Table(name = "THEME_PARTS")
public class ThemeParts extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "THEME_PARTS_KEY", nullable = false)
    private Long key;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "THEME_KEY", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Theme theme;

    @Description("파츠에서 대표 파츠 키만 엮어야 한다.")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PARTS_KEY", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Parts parts;

    @Description("테마별로 다르게 적용되는 부위(파츠 기준)")
    @Column(name = "POSITION")
    @Enumerated(EnumType.STRING)
    private ThemePosition position;

    @Description("삭제처리 여부")
    @Builder.Default
    @Column(name = "DELETED", nullable = false)
    private Boolean deleted = false;

    public ThemeParts deleteThemeParts() {
        this.deleted = true;
        return this;
    }
}
