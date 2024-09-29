package kitten.core.corecommon.security.filter;

import kitten.core.corecommon.properties.CorsProperties;
import kitten.core.corecommon.security.oauth2.CorsAutoConfiguration;
import kitten.core.corecommon.security.oauth2.Oauth2AuthorizationRequestRepository;
import kitten.core.corecommon.security.oauth2.Oauth2CustomUserService;
import kitten.core.corecommon.security.oauth2.Oauth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@AutoConfiguration(after = {CorsAutoConfiguration.class})
@RequiredArgsConstructor
public class CustomSecurityConfiguration {

    private final ApplicationEventPublisher publisher;
    private final Oauth2SuccessHandler oauth2SuccessHandler;
    private final Oauth2AuthorizationRequestRepository oauth2AuthorizationRequestRepository;

    @Bean(name = "customCorsConfig")
    public CorsConfigurationSource customCorsConfig(CorsProperties properties) {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(properties.isAllowCredential());
        config.setAllowedOriginPatterns(properties.getAllowOriginPatterns());
        config.setAllowedHeaders(properties.getAllowedHeaders());
        config.setAllowedMethods(properties.getAllowedMethods());
        config.setExposedHeaders(properties.getExposedHeaders());
        config.setMaxAge(properties.getMaxAge());

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain permitAllFilterChain(HttpSecurity http,
                                                    CorsConfigurationSource customCorsConfig) throws Exception {
        http
//                .securityMatcher("/favicon.ico")
                .authorizeHttpRequests((authorize) -> authorize.requestMatchers("/**").permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .cors(corsConfigurer -> corsConfigurer.configurationSource(customCorsConfig))
                .sessionManagement(sessionConfigurer -> sessionConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http
                .oauth2Login(
                        oauth2 -> oauth2
                                .authorizationEndpoint(
                                        endpoint -> endpoint.baseUri("/oauth2/authorize/**")
                                                .authorizationRequestRepository(oauth2AuthorizationRequestRepository)
                                )
                                .redirectionEndpoint(
                                        redirect -> redirect.baseUri("/oauth2/callback/**")
                                )
                                .userInfoEndpoint(
                                        userInfo -> userInfo.userService(new Oauth2CustomUserService(publisher))
                                )
                                .successHandler(oauth2SuccessHandler)
                );
        return http.build();
    }
}
