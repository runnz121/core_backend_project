package kitten.core.coredomain.config.orm;

import jakarta.persistence.EntityManager;
import kitten.core.coredomain.config.properties.JpaAuditingProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@AutoConfiguration
@ConditionalOnClass({
        EntityManager.class,
        EnableWebSecurity.class,
        LocalContainerEntityManagerFactoryBean.class
})
@ConditionalOnProperty(
        prefix = "core.jpa.audit",
        name = "enabled",
        matchIfMissing = true
)
@Import(JpaAuditingConfiguration.class)
@EnableConfigurationProperties(JpaAuditingProperties.class)
public class JpaAuditingAutoConfiguration {
}
