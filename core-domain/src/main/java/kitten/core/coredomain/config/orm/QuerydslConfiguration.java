package kitten.core.coredomain.config.orm;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration(after = HibernateJpaAutoConfiguration.class)
//@ConditionalOnBean(EntityManagerFactory.class)
@ConditionalOnClass({JPAQueryFactory.class})
@ConditionalOnProperty(prefix = "core.jpa.querydsl", name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(QuerydslProperties.class)
@RequiredArgsConstructor
public class QuerydslConfiguration {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    @ConditionalOnMissingBean(name = "jpaQueryFactory", value = JPAQueryFactory.class)
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
