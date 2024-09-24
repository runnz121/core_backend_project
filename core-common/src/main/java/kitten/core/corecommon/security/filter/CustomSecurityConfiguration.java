package kitten.core.corecommon.security.filter;

import kitten.core.corecommon.security.oauth2.Oauth2AccessDeniedHandler;
import kitten.core.corecommon.security.oauth2.Oauth2CustomUserService;
import kitten.core.corecommon.security.oauth2.Oauth2EntryPointHandler;
import kitten.core.corecommon.security.oauth2.Oauth2SuccessHandler;
import kitten.core.corecommon.security.service.TokenService;
import kitten.core.corecommon.security.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
public class CustomSecurityConfiguration {

    private final ApplicationEventPublisher publisher;

    private final Oauth2SuccessHandler oauth2SuccessHandler;

    private final UserDetailService userDetailService;
    private final TokenService tokenService;

    @Description("이 설정은 스프링 시큐리티 필터 체인을 타지 않는다")
    @Bean
    @Order(1)
    public SecurityFilterChain permitAllFilterChain(HttpSecurity http,
                                                    CorsConfigurationSource configurationSource) throws Exception {
        http
                .securityMatcher(request ->
                        !request.getRequestURI().contains("oauth2")
//                                || !request.getRequestURI().contains("auth")
                )
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .cors(corsConfigurer -> corsConfigurer.configurationSource(configurationSource))
                .sessionManagement(sessionConfigurer -> sessionConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http
                .oauth2Login(
                        oauth2 -> oauth2
                                .authorizationEndpoint(
                                        endpoint -> endpoint.baseUri("/oauth2/authorize")
                                )
                                .redirectionEndpoint(
                                        redirect -> redirect.baseUri("/oauth2/callback/**")
                                )
                                .userInfoEndpoint(
                                        userInfo -> userInfo.userService(new Oauth2CustomUserService(publisher)))
                                .successHandler(oauth2SuccessHandler)
                );
        http
                .addFilterBefore(new AuthorizationFilter(authenticationManager(userDetailService, passwordEncoder()), userDetailService, tokenService), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailService userDetailsService,
                                                       PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
