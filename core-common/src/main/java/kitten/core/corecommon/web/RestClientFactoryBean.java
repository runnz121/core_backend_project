package kitten.core.corecommon.web;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

public class RestClientFactoryBean implements FactoryBean<Object>, ApplicationContextAware {

    @Nullable
    private String webClientBeanName;
    @Nullable
    private Class<?> restClientClass;
    @Nullable
    private ApplicationContext applicationContext;

    @Override
    public Object getObject() throws Exception {
        Assert.state(applicationContext != null, "applicationContext cannot be null");
        Assert.state(restClientClass != null, "restClientClass cannot be null");
        Assert.state(webClientBeanName != null, "webClientBeanName cannot be null");

        WebClient webClient = applicationContext.getBean(webClientBeanName, WebClient.class);

        Assert.state(webClient != null, "WebClient bean with webClientBeanName = " + webClientBeanName + " not found");

        WebClientAdapter clientAdapter = WebClientAdapter.create(webClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(clientAdapter).build();

        return httpServiceProxyFactory.createClient(restClientClass);
    }

    @Override
    public Class<?> getObjectType() {
        return restClientClass;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void setWebClientBeanName(String webClientBeanName) {
        this.webClientBeanName = webClientBeanName;
    }
}
