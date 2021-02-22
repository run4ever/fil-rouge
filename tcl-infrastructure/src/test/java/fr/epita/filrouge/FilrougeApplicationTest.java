package fr.epita.filrouge;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootConfiguration
@ComponentScan(basePackages = { "fr.epita.filrouge" }, lazyInit = true)
@EntityScan(basePackages = { "fr.epita.filrouge.infrastructure.*"})
@EnableJpaRepositories
public class FilrougeApplicationTest {
    @Test
    void contextLoads() {
    }

   /* public static void main(String[] args) {
        SpringApplication.run (FilrougeApplicationTest.class, args);
    }*/
}
