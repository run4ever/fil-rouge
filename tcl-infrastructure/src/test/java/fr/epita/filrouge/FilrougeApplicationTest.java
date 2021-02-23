package fr.epita.filrouge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = { "fr.epita.filrouge" }, lazyInit = true)
@EntityScan(basePackages = { "fr.epita.filrouge.infrastructure.*"})
public class FilrougeApplicationTest {
    public static void main(String[] args) {SpringApplication.run (FilrougeApplicationTest.class, args); }
}
