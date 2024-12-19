package kitten.core.coredomain.board.entity;

import jakarta.persistence.*;
import kitten.core.coredomain.config.annotation.Description;
import kitten.core.coredomain.model.Audit;
import kitten.core.coredomain.moru.entity.MoruUserArtInfo;
import kitten.core.coredomain.moru.entity.MoruUserPart;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"key"}, callSuper = false)
@Entity
@Description("게시글과 연관된 유저 생성 아이템 맵핑")
@Table(name = "BOARD_ITEM")
public class BoardItem extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_ITEM_KEY", nullable = false)
    private Long key;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BOARD_KEY", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Board board;

    @Description("유저 생성 작품 연관 키")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MORU_USER_ART_INFO_KEY", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private MoruUserArtInfo userArtInfo;

    @Description("삭제처리 여부")
    @Builder.Default
    @Column(name = "DELETED", nullable = false)
    private Boolean deleted = false;

    public BoardItem deleteBoardItem() {
        this.deleted = true;
        return this;
    }
}
