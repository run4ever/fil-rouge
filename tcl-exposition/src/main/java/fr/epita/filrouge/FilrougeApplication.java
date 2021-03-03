package fr.epita.filrouge;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class FilrougeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilrougeApplication.class, args);
        System.out.println("Let's start ! ");
    }
}
