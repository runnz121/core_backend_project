package kitten.core.coredomain.board.entity;

import jakarta.persistence.*;
import kitten.core.coredomain.model.Audit;
import kitten.core.coredomain.user.entity.Users;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"key"}, callSuper = false)
@Entity
@Table(name = "BOARD")
public class Board extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_KEY", nullable = false)
    private Long key;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_KEY", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Users user;

    @Column(name = "COMMENT")
    private String comment;

    public String getWriterNickName() {
        return user.getNickName();
    }
}
