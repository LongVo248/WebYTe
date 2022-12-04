package mtt.webyte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "mtt.webyte.repository")
//@EntityScan(basePackages = "entities-package")
public class WebYTeApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebYTeApplication.class, args);
    }

}
