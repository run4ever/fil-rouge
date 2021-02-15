package fr.epita.filrouge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
public class FilrougeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilrougeApplication.class, args);

        System.out.println("Let's start ! ");

    }

}
