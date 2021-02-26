package fr.epita.filrouge.exposition.person;


import fr.epita.filrouge.application.person.AppUserLightDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PersonResourceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void show_appuser_by_email() throws Exception {
        //Given
        String emailTest ="arnaud%40tcl.com";

        //When
        ResponseEntity<AppUserLightDto> response = restTemplate.getForEntity("/api/v1/appuser/"+emailTest, AppUserLightDto.class);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        //TODO à améliorer
       // assertThat(response.getBody().getEmail()).isEqualTo(emailTest);
    }
}
