package kitten.core.coredomain.config.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "core.datasource.default.read")
public class DefaultReadDataSourceProperties {

    private String driverClassName;
    private String jdbcUrl;
    private String username;
    private String password;
}
