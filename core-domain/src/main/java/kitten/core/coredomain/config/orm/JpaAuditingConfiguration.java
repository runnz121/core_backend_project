package kitten.core.coredomain.config.orm;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.AuditingHandler;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.lang.Nullable;

@Slf4j
@EnableJpaAuditing
@Configuration
public class JpaAuditingConfiguration {

    @PostConstruct
    public void init() {
    }

    @Bean
    @ConditionalOnMissingBean
    public AuditorAware<String> auditorAware() {
        return new JwtAuditorAware();
    }

    @Bean
    @ConditionalOnMissingBean
    public CustomAuditEntityListener customAuditEntityListener(@Nullable ObjectFactory<AuditingHandler> handler) {
        return new CustomAuditEntityListener(handler, auditorAware());
    }
}
