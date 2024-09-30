package kitten.core.coredomain.theme.entity;

import jakarta.persistence.*;
import kitten.core.coredomain.config.annotation.Description;
import kitten.core.coredomain.model.Audit;
import kitten.core.coredomain.theme.consts.ThemeType;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"key"}, callSuper = false)
@Entity
@Table(name = "THEME")
public class Theme extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "THEME_KEY", nullable = false)
    private Long key;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Description("테마 타입")
    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private ThemeType type;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "THEME_IMAGE_URL", nullable = false)
    private String themeImgUrl;

    public boolean isAllTheme() {
        return ThemeType.ALL == type;
    }
}
