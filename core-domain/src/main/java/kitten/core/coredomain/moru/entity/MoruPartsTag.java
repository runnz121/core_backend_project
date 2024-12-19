package kitten.core.coredomain.moru.entity;

import jakarta.persistence.*;
import kitten.core.coredomain.config.annotation.Description;
import kitten.core.coredomain.model.Audit;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLRestriction("deleted = false")
@EqualsAndHashCode(of = {"key"}, callSuper = false)
@Entity
@Table(name = "MORU_PARTS_TAG")
public class MoruPartsTag extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MORU_PARTS_TAG_KEY", nullable = false)
    private Long key;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MORU_PARTS", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private MoruParts moruParts;

    @Column(name = "TAG", nullable = false)
    private String tag;

    @Description("삭제처리 여부")
    @Builder.Default
    @Column(name = "DELETED", nullable = false)
    private Boolean deleted = false;

    public MoruPartsTag deletePartsTag() {
        this.deleted = true;
        return this;
    }
}
