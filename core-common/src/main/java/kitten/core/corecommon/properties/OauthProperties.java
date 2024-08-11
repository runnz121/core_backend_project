package kitten.core.corecommon.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "core.security.oauth2")
public class OauthProperties {

    private String jwtSigningKey;
    private String principalClaimName = "user_email";
    private String authoritiesClaimName = "authorities";
    private String authorityPrefix = "";
    private String authorizationCookieName = "auth";
}
