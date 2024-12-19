package kitten.core.coredomain.board.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"key"}, callSuper = false)
@Entity
@Table(name = "BOARD_IMAGE")
public class BoardImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_IMAGE_KEY", nullable = false)
    private Long key;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BOARD_KEY", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Board board;

    @Column(name = "IMAGE_URL", nullable = false)
    private String imageUrl;

    @Column(name = "REPRESENTATIVE")
    private Boolean representative;

    @Column(name = "SORT")
    private Integer sort;

    @Builder.Default
    @Column(name = "DELETED", nullable = false)
    private Boolean deleted = false;

    public BoardImage deleteBoardImage() {
        this.deleted = true;
        return this;
    }
}
