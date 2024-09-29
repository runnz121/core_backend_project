package kitten.core.corecommon.security.filter;

import kitten.core.corecommon.security.oauth2.CorsAutoConfiguration;
import kitten.core.corecommon.security.oauth2.Oauth2AuthorizationRequestRepository;
import kitten.core.corecommon.security.oauth2.Oauth2CustomUserService;
import kitten.core.corecommon.security.oauth2.Oauth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

@AutoConfiguration(after = {CorsAutoConfiguration.class})
@RequiredArgsConstructor
public class CustomSecurityConfiguration {

    private final ApplicationEventPublisher publisher;
    private final Oauth2SuccessHandler oauth2SuccessHandler;
    private final Oauth2AuthorizationRequestRepository oauth2AuthorizationRequestRepository;

    @Description("이 설정은 스프링 시큐리티 필터 체인을 타지 않는다")
    @Bean
    @Order(1)
    public SecurityFilterChain permitAllFilterChain(HttpSecurity http,
                                                    CorsConfigurationSource configurationSource) throws Exception {
        http
//                .securityMatcher("/favicon.ico")
                .authorizeHttpRequests((authorize) -> authorize.requestMatchers("/**").permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .cors(corsConfigurer -> corsConfigurer.configurationSource(configurationSource))
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
