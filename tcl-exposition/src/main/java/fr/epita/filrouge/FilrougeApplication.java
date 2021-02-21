package fr.epita.filrouge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"fr.epita.filrouge"})


@EnableSwagger2
@EnableJpaRepositories(basePackages = {"fr.epita.filrouge"})
@EntityScan("fr.epita.filrouge")
public class FilrougeApplication {

    private static final Logger LOG = LoggerFactory.getLogger(FilrougeApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(FilrougeApplication.class, args);

        LOG.info("Let's start ! ");
        LOG.info("swagger link : http://localhost:8080/swagger-ui.html");


    }

}
