package kitten.core.coredomain.board;

import jakarta.persistence.*;
import kitten.core.coredomain.model.Audit;
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

    @Column(name = "COMMENT")
    private String comment;
}
