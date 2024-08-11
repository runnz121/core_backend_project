package kitten.core.coredomain.config.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "core.jpa.audit")
public class JpaAuditingProperties {

    private boolean enabled = true;
}
