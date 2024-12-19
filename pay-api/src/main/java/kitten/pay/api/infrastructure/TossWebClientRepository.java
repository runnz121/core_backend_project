package kitten.pay.api.infrastructure;

import kitten.core.corecommon.KittenRestClient;
import org.springframework.http.MediaType;
import org.springframework.web.service.annotation.HttpExchange;

@KittenRestClient(webClientBeanName = "tossWebClient")
@HttpExchange(contentType = MediaType.APPLICATION_JSON_VALUE, accept = MediaType.APPLICATION_JSON_VALUE)
public interface TossWebClientRepository {
}
