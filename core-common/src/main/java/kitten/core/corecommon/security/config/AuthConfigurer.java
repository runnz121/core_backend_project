package kitten.core.corecommon.security.config;

import kitten.core.corecommon.security.interceptor.AuthInterceptor;
import kitten.core.corecommon.security.jwt.CurrentAccount;
import kitten.core.corecommon.security.resolver.AuthArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class AuthConfigurer implements WebMvcConfigurer {

    private final CurrentAccount currentAccount;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new AuthArgumentResolver(currentAccount));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(
                        AuthInterceptor.builder()
//                                .authExtractor(authExtractor)
//                                .tokenExtractor(tokenExtractor)
                                .currentAccount(currentAccount)
                                .build())
                .addPathPatterns("/**");
//                .excludePathPatterns("/**","/szs/signup", "/szs/login", "/3o3/**");
    }
}
