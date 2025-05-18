package kitten.diy.api.infrastructure.config.redis;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemRedissonSubscriber {

    private static final String ITEM_TOPIC = "itemTopic";
    private static final String ITEM_CACHE_KEY = "ITEM";
    private static final String ITEM_CAFFEINE_CACHE = "itemCaffeineCache";

    private final CacheManager cacheManager;
    private final RedissonClient redissonClient;

    @PostConstruct
    public void subscribe() {

        RTopic topic = redissonClient.getTopic(ITEM_TOPIC);

        topic.addListener((MessageListener<String>) (channel, msg) -> {

            if (cacheManager.getCache(ITEM_CAFFEINE_CACHE) == null) return;

            cacheManager.getCache(ITEM_CAFFEINE_CACHE).evict(ITEM_CACHE_KEY);

            log.info("<REDISSON:SUBSCRIBER:수신> itemCaffeineCache 캐시 삭제");
        });
    }
}
