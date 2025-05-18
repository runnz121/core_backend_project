package kitten.core.coredomain.config.redis;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.Duration;

@Configuration
@EnableCaching
public class CaffeineCacheConfig {

    private static final String ITEM_CAFFEINE_CACHE = "itemCaffeineCache";

    @Bean
    @Primary
    public CacheManager cacheManager() {

        CaffeineCacheManager cacheManager = new CaffeineCacheManager(ITEM_CAFFEINE_CACHE);

        cacheManager.setCaffeine(Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(500)
                .expireAfterWrite(Duration.ofMinutes(10))
                .recordStats());

        return cacheManager;
    }
}
