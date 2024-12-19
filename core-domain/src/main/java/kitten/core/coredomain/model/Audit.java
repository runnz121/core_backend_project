package kitten.core.coredomain.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import kitten.core.coredomain.config.orm.CustomAuditEntityListener;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(CustomAuditEntityListener.class)
public abstract class Audit implements Serializable  {

    /** 생성자 */
    @CreatedBy
    @Column(name = "CREATE_BY", length = 50, nullable = false)
    protected String createBy;

    /** 생성시간 */
    @CreatedDate
    @Column(name = "CREATE_TIME", nullable = false, updatable = false)
    protected LocalDateTime createTime;

    /** 수정자 */
    @LastModifiedBy
    @Column(name = "UPDATE_BY", length = 50, nullable = false)
    protected String updateBy;

    /** 수정시간 */
    @LastModifiedDate
    @Column(name = "UPDATE_TIME", nullable = false)
    protected LocalDateTime updateTime;

    public void changeCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public void changeUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}
