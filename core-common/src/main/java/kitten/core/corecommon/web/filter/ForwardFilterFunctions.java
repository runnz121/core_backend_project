package kitten.core.corecommon.web.filter;

import org.slf4j.MDC;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;

public class ForwardFilterFunctions implements ExchangeFilterFunction {

    public static final String X_FORWARDED_FOR_VALUE = "X-Forwarded-For";

    @Override
    public Mono<ClientResponse> filter(ClientRequest request,
                                       ExchangeFunction next) {
        return forwardHeader(request)
                .flatMap(next::exchange);
    }

    private Mono<ClientRequest> forwardHeader(ClientRequest request) {
        return Mono.just(
                ClientRequest.from(request)
                        .header(X_FORWARDED_FOR_VALUE, MDC.get(X_FORWARDED_FOR_VALUE))
                        .build()
        );
    }
}
