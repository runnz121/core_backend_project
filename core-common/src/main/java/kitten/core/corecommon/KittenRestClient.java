package kitten.core.corecommon;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface KittenRestClient {

    String webClientBeanName();
}
