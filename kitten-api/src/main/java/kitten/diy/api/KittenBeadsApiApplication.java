package kitten.diy.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@ComponentScan(basePackages = "kitten.core.corecommon.*")
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
public class KittenBeadsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KittenBeadsApiApplication.class, args);
    }
}
