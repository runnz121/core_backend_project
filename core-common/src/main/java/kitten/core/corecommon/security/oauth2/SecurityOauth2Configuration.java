package kitten.core.corecommon.security.oauth2;

import kitten.core.corecommon.properties.OauthProperties;
import kitten.core.corecommon.security.filter.AuthorizationFilter;
import kitten.core.corecommon.security.service.TokenService;
import kitten.core.corecommon.security.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@AutoConfiguration(after = {CorsAutoConfiguration.class})
@ConditionalOnProperty(prefix = "core.security.oauth2", name = "jwt-signing-key")
@ConditionalOnClass({EnableWebSecurity.class, EnableMethodSecurity.class})
@EnableWebSecurity(debug = true)
@EnableMethodSecurity(securedEnabled = true)
@EnableConfigurationProperties(OauthProperties.class)
@RequiredArgsConstructor
@Import({
        Oauth2SuccessHandler.class,
        Oauth2EntryPointHandler.class,
        Oauth2AccessDeniedHandler.class,
        UserDetailService.class,
        TokenService.class
})
public class SecurityOauth2Configuration {

    private final ApplicationEventPublisher publisher;

    private final Oauth2SuccessHandler oauth2SuccessHandler;
    private final Oauth2EntryPointHandler oauth2EntryPointHandler;
    private final Oauth2AccessDeniedHandler oauth2AccessDeniedHandler;

    private final UserDetailService userDetailService;
    private final TokenService tokenService;

    @Bean
    @ConditionalOnBean(CorsConfigurationSource.class)
    public SecurityFilterChain oauth2FilterChain(HttpSecurity http,
                                                 CorsConfigurationSource configurationSource) throws Exception {
        http
                // h2-console 접속을 위해 추가
//                .headers(headersConfigurer -> headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .cors(corsConfigurer -> corsConfigurer.configurationSource(configurationSource))
                .exceptionHandling(handleConfigurer ->
                        handleConfigurer
                                .authenticationEntryPoint(oauth2EntryPointHandler)
                                .accessDeniedHandler(oauth2AccessDeniedHandler)
                )
                .sessionManagement(sessionConfigurer -> sessionConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers("/actuator/**", "/oauth2/**", "/**").permitAll()
//                                .anyRequest().authenticated()
                                // 임시로 모두 허용
//                                .requestMatchers("/**").permitAll()
//                                .requestMatchers("/auth/**").authenticated()
//                                .requestMatchers("/actuator/**").permitAll()
//                                .anyRequest().authenticated()
                );
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
