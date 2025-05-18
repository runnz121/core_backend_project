package kitten.diy.api.infrastructure.config.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemRedissonPublisher {

    private static final String ITEM_TOPIC = "itemTopic";

    private final RedissonClient redissonClient;

    public void publish(String message) {

        RTopic topic = redissonClient.getTopic(ITEM_TOPIC);

        long receivers = topic.publish(message);

        log.info("<REDISSON:PUBLISHER:메시지 발행> 메시지 수신 : {}", receivers);
    }
}
