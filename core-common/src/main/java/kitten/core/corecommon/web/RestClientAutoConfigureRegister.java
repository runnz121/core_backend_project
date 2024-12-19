package kitten.core.corecommon.web;

import kitten.core.corecommon.KittenRestClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.util.Collections;
import java.util.List;

public class RestClientAutoConfigureRegister implements ImportBeanDefinitionRegistrar, BeanFactoryAware {

    @Nullable
    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry,
                                        BeanNameGenerator importBeanNameGenerator) {
        registerClients(resolveClientsInMainPackages(), registry);
    }

    private void registerClients(Iterable<RestClientCandidate> clientCandidates,
                                 BeanDefinitionRegistry registry) {
        for (RestClientCandidate clientCandidate : clientCandidates) {

            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(RestClientFactoryBean.class);
            builder.addPropertyValue("restClientClass", clientCandidate.restClientClass());
            builder.addPropertyValue("webClientBeanName", clientCandidate.webClientBeanName());
            registry.registerBeanDefinition(clientCandidate.restClientBeanName(), builder.getBeanDefinition());
        }
    }

    private List<RestClientCandidate> resolveClientsInMainPackages() {
        Assert.state(beanFactory != null, "beanFactory cannot be null");

        List<String> mainPackages = resolveMainPackages();

        if (!mainPackages.isEmpty()) {
            ClassPathScanningCandidateComponentProvider scanner = getScanner();
            scanner.addIncludeFilter(new AnnotationTypeFilter(KittenRestClient.class));

            List<RestClientCandidate> candidates = mainPackages.stream()
                    .flatMap(it -> scanner.findCandidateComponents(it).stream())
                    .filter(it -> it.getBeanClassName() != null)
                    .map(this::createHntRestClientCandidate)
                    .toList();

            return candidates;
        }
        return Collections.emptyList();
    }

    private List<String> resolveMainPackages() {
        try {
            return AutoConfigurationPackages.get(beanFactory);
        } catch (IllegalStateException e) {
            return Collections.emptyList();
        }
    }

    private ClassPathScanningCandidateComponentProvider getScanner() {
        return new ClassPathScanningCandidateComponentProvider(false) {
            @Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                boolean isCandidate = false;
                if (beanDefinition.getMetadata().isIndependent()) {
                    if (!beanDefinition.getMetadata().isAnnotation()) {
                        isCandidate = true;
                    }
                }
                return isCandidate;
            }
        };
    }

    private RestClientCandidate createHntRestClientCandidate(BeanDefinition beanDefinition) {
        try {
            Class<?> clazz = ClassUtils.forName(beanDefinition.getBeanClassName(), null);
            KittenRestClient kittenRestClientAnnotation = AnnotationUtils.findAnnotation(clazz, KittenRestClient.class);

            Assert.state(kittenRestClientAnnotation != null, "@KittenRestClient annotation not found on class " + beanDefinition);
            return new RestClientCandidate(kittenRestClientAnnotation.webClientBeanName(), clazz);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public record RestClientCandidate(String webClientBeanName,
                                      Class<?> restClientClass) {
        String restClientBeanName() {
            return restClientClass.getSimpleName();
        }
    }
}
