package kitten.beads.kittenbeadsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"kitten.core.*"})
@EnableJpaRepositories(basePackages = {"kitten.core.*"})
public class KittenBeadsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KittenBeadsApiApplication.class, args);
    }
}
