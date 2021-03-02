package fr.epita.filrouge.exposition.controller;


import fr.epita.filrouge.application.person.AppUserDto;
import fr.epita.filrouge.application.person.AppUserLightDto;
import fr.epita.filrouge.domain.entity.person.Role;
import fr.epita.filrouge.domain.exception.AlreadyExistingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PersonResourceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void show_appuser_with_email_existing_should_be_sucess() throws Exception {
        //Given
        //Cette adresse email est dans le fichier import.sql qui sera chargé à l'initialisation de l'application
        String emailTest ="superman@world.com";

        //When
        ResponseEntity<AppUserLightDto> response = restTemplate.getForEntity("/api/v1/appuser/"+emailTest, AppUserLightDto.class);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getEmail()).isEqualTo(emailTest);
        assertThat(response.getBody().getLastname()).isEqualTo("man");
    }

    @Test
    public void email_not_exists_should_return_not_found404() throws Exception {
        //Given
        //Cette adresse email ne devait pas exister
        String emailTest ="zzzzzzzzzzzzzzzzzyyyyyyyyy@test.test";

        //When
        ResponseEntity<AppUserLightDto> response = restTemplate.getForEntity("/api/v1/appuser/"+emailTest, AppUserLightDto.class);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void add_exiting_email_should_return_AlreadyExistingException() {
        //Given
        //"superman@world.com" est dans le fichier import.sql qui sera chargé à l'initialisation de l'application
        // si on recrée un appUser alors la violation de clé
        String emailTest ="superman@world.com";
        AppUserDto appUser = AppUserDto.Builder.anAppUserDto()
                .withEmail("superman@world.com")
                .withPassword("superman")
                .withFirstname("Super")
                .withLastname("man")
                .withBirthdayDate(LocalDate.of(2000,12,25))
                .withRole(Role.ROLE_USER)
                .build();

        //When
        try {
            ResponseEntity<AppUserDto> response = restTemplate.postForEntity("/api/v1/appuser/add", appUser, AppUserDto.class);
        } catch (Exception e) {
        //Then
            assertThat(e).isInstanceOf(AlreadyExistingException.class);
        }
    }

    @Test
    public void add_appUser_should_be_sucess() {
        //Given
        AppUserDto appUser = AppUserDto.Builder.anAppUserDto()
                .withEmail("email_super_cas_test_99999@test.com")
                .withPassword("supertest")
                .withFirstname("Test")
                .withLastname("You")
                .withBirthdayDate(LocalDate.of(2000,12,25))
                .withRole(Role.ROLE_USER)
                .build();

        //When
        ResponseEntity<AppUserDto> response = restTemplate.postForEntity("/api/v1/appuser/add", appUser, AppUserDto.class);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}
