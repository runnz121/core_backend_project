package kitten.beads.kittenbeadsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

//@ComponentScan(basePackages = "kitten.core.corecommon.*")
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
public class KittenBeadsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KittenBeadsApiApplication.class, args);
    }
}
