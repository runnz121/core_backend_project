package kitten.core.coredomain.board.entity;

import jakarta.persistence.*;
import kitten.core.coredomain.config.annotation.Description;
import kitten.core.coredomain.model.Audit;
import kitten.core.coredomain.user.entity.Users;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"key"}, callSuper = false)
@Entity
@Table(name = "BOARD_VIEW")
public class BoardView extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_VIEW_KEY", nullable = false)
    private Long key;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BOARD_KEY", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Board board;

    @Description("조회한 한 유저 키")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_KEY", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Users users;
}
