package kitten.core.corecommon.web;

import kitten.core.corecommon.web.filter.ClientFilterFunctions;
import kitten.core.corecommon.web.filter.ForwardFilterFunctions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.security.oauth2.server.resource.web.reactive.function.client.ServletBearerExchangeFilterFunction;

@Slf4j
@AutoConfiguration
@ConditionalOnClass(WebClient.class)
@ConditionalOnProperty(prefix = "hnt.client", name = "enabled", matchIfMissing = true)
@Import(RestClientAutoConfigureRegister.class)
@EnableConfigurationProperties(ClientProperties.class)
public class RestClientConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ClientFilterFunctions hntClientFilterFunctions() {
        log.debug("### HntAutoconfigure Bean: '{}'", "hntClientFilterFunctions");

        return new ClientFilterFunctions(
//                new ServletBearerExchangeFilterFunction(),
                new ForwardFilterFunctions()
        );
    }
}
