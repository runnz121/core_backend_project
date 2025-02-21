package kitten.core.coredomain.board.entity;

import jakarta.persistence.*;
import kitten.core.coredomain.board.consts.BoardPostStatus;
import kitten.core.coredomain.board.consts.BoardType;
import kitten.core.coredomain.config.annotation.Description;
import kitten.core.coredomain.model.Audit;
import kitten.core.coredomain.user.entity.Users;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_KEY", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Users user;

    @Description("게시글 노출 아이템 타입")
    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private BoardType type;

    @Description("게시글 게시 상태")
    @Column(name = "POST_STATUS")
    @Enumerated(EnumType.STRING)
    private BoardPostStatus postStatus;

    @Column(name = "COMMENT")
    private String comment;

    @Builder.Default
    @Column(name = "DELETED", nullable = false)
    private Boolean deleted = false;

    public String getWriterNickName() {
        return user.getNickName();
    }

    public Board deleteBoard() {
        this.deleted = true;
        return this;
    }
}
