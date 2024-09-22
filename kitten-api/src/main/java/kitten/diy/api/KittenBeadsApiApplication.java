package kitten.diy.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "kitten.*")
//@ComponentScans({
//        @ComponentScan(basePackages = "kitten.core.corecommon.utils"),
//        @ComponentScan(basePackages = "kitten.core.coredomain.config.aws.*")
//})
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
public class KittenBeadsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KittenBeadsApiApplication.class, args);
    }
}
