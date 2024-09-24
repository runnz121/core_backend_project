package kitten.core.corecommon.security.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class CustomSecurityConfiguration {

    @Description("이 설정은 스프링 시큐리티 필터 체인을 타지 않는다")
    @Bean
    @Order(1)
    public SecurityFilterChain permitAllFilterChain(HttpSecurity http,
                                                    CorsConfigurationSource configurationSource) throws Exception {
        http
                .securityMatcher(request ->
                        !request.getRequestURI().contains("oauth2") || !request.getRequestURI().contains("auth")
                )
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .cors(corsConfigurer -> corsConfigurer.configurationSource(configurationSource))
                .sessionManagement(sessionConfigurer -> sessionConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}
