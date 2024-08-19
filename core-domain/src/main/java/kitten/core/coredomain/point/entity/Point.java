package kitten.core.coredomain.point.entity;

import jakarta.persistence.*;
import kitten.core.coredomain.model.Audit;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"key"}, callSuper = false)
@Entity
@Table(name = "POINT")
public class Point extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POINT_KEY", nullable = false)
    private Long key;

    // 만료기간
    @Column(name = "EXPIRE_DATE", nullable = false)
    private LocalDateTime expireDate;
}
