package kitten.core.corecommon.security.oauth2;

import kitten.core.corecommon.properties.CorsProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@AutoConfiguration(after = DispatcherServletAutoConfiguration.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnProperty(prefix = "core.cors", name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(CorsProperties.class)
public class CorsAutoConfiguration {

    @Primary
    @Bean
    public CorsConfigurationSource corsConfigurationSource(CorsProperties properties) {
        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(properties.isAllowCredential());
        config.setAllowedOriginPatterns(properties.getAllowOriginPatterns());
        config.setAllowedHeaders(properties.getAllowedHeaders());
        config.setAllowedMethods(properties.getAllowedMethods());
        config.setExposedHeaders(properties.getExposedHeaders());
        config.setMaxAge(properties.getMaxAge());

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
