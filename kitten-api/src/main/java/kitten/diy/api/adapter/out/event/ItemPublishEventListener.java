package kitten.diy.api.adapter.out.event;

import kitten.diy.api.adapter.out.model.ItemEventData;
import kitten.diy.api.infrastructure.config.redis.ItemRedissonPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemPublishEventListener {

    private final ItemRedissonPublisher itemRedissonPublisher;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void publishItemRegister(ItemEventData event) {

        try {
            itemRedissonPublisher.publish(event.message());
            log.info("<REDISSON:PUBLISH:이벤트 발행> 메세지 : {}", event.message());
        } catch (Exception ex) {
            log.error("<REDISSON:PUBLISH:에러발생>", ex);
        }
    }
}
