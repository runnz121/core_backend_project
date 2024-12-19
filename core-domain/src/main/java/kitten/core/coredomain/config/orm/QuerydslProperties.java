package kitten.core.coredomain.config.orm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "core.jpa.querydsl")
public class QuerydslProperties {

    private boolean enabled = true;
}
