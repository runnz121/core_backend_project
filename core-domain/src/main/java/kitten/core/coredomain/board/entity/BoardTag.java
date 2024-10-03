package kitten.core.coredomain.board.entity;

import jakarta.persistence.*;
import kitten.core.coredomain.config.annotation.Description;
import kitten.core.coredomain.model.Audit;
import kitten.core.coredomain.moru.entity.MoruUserPart;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"key"}, callSuper = false)
@Entity
@Table(name = "BOARD_TAG")
public class BoardTag extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_TAG_KEY", nullable = false)
    private Long key;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BOARD_KEY", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Board board;

    @Column(name = "TAG", nullable = false)
    private String tag;

    @Description("삭제처리 여부")
    @Builder.Default
    @Column(name = "DELETED", nullable = false)
    private Boolean deleted = false;

    public BoardTag deleteBoardTag() {
        this.deleted = true;
        return this;
    }
}
