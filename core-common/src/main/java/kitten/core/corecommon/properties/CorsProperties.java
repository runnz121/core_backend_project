package kitten.core.corecommon.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@Setter
@ConfigurationProperties(prefix = "core.cors")
public class CorsProperties {

    private boolean enabled = true;
    private List<String> allowOriginPatterns = List.of(
            "http://localhost:[*]",
            "https://localhost:[*]",
            "http://diykitten.com:[*]",
            "https://diykitten.com:[*]",
            "https://www.diykitten.com:[*]",
            "http://13.124.132.215:[*]",
            "https://core-api.diykitten-backend.site:[*]"
    );
    private boolean allowCredential = true;
    private long maxAge = 3600L;
    private List<String> allowedHeaders = List.of("*");
    private List<String> allowedMethods = List.of("*");
    private List<String> exposedHeaders = List.of("*");
}
