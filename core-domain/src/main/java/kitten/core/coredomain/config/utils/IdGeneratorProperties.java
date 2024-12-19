package kitten.core.coredomain.config.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "id.gen")
public class IdGeneratorProperties {

    private boolean enabled = true;
}
