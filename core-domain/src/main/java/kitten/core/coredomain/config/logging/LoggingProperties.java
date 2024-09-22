package kitten.core.coredomain.config.logging;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "core.logging")
public class LoggingProperties {

    private boolean enabled = true;
}
