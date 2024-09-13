package kitten.core.coredomain.config.utils;

import kitten.core.coredomain.utils.IdGenerator;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ThreadLocalRandom;

@AutoConfiguration
@ConditionalOnProperty(prefix = "id.gen", name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(IdGeneratorProperties.class)
public class IdGeneratorConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public IdGenerator idGenerator() {
        return new IdGenerator(ThreadLocalRandom.current());
    }
}
