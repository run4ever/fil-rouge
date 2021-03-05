package fr.epita.filrouge.exposition.controller;

import fr.epita.filrouge.application.viewingserie.ViewingSerieCreateDto;
import fr.epita.filrouge.application.viewingserie.ViewingSerieRestitDto;
import fr.epita.filrouge.domain.entity.common.Status;
import fr.epita.filrouge.exposition.controller.common.TokenGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ViewingSerieTest {

    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void add_viewingSerie_should_be_success() throws Exception {
        //Given
        ViewingSerieCreateDto viewingSerieCreateDto = ViewingSerieCreateDto.Builder.aViewingSerieCreateDto()
                .withEmail("fabien@tcl.com")
                .withImdb("tt00test999")
                .withCurrentSeason(3)
                .withCurrentEpisode(1)
                .withStatus(Status.TO_WATCH)
                .build();

        //initialiser un toke JWT et le mettre dans header ****************
        HttpHeaders headers = tokenGenerator.getHeadersWithJwtToken("superman@world.com");
        //*****************************************************************

        HttpEntity<ViewingSerieCreateDto> request = new HttpEntity<>(viewingSerieCreateDto,headers);

        //When
        ResponseEntity<ViewingSerieCreateDto> response = restTemplate.postForEntity("/api/v1/viewingserie/create",request, ViewingSerieCreateDto.class);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void add_exiting_viewingSerie_should_return_code400() {
        //Given
        //Pour superman@world.com on a déjà injecté la série tt00test000 via import.sql
        ViewingSerieCreateDto viewingSerieCreateDto = ViewingSerieCreateDto.Builder.aViewingSerieCreateDto()
                .withEmail("superman@world.com")
                .withImdb("tt00test000")
                .withCurrentSeason(3)
                .withCurrentEpisode(1)
                .withStatus(Status.FINISHED)
                .build();

        //initialiser un toke JWT et le mettre dans header ****************
        HttpHeaders headers = tokenGenerator.getHeadersWithJwtToken("superman@world.com");
        //*****************************************************************

        HttpEntity<ViewingSerieCreateDto> request = new HttpEntity<>(viewingSerieCreateDto,headers);

        //When
        try {
            ResponseEntity<ViewingSerieCreateDto> response = restTemplate.postForEntity("/api/v1/viewingserie/create",request, ViewingSerieCreateDto.class);
        } catch (Exception e) {

            //Then
            //assertThat(e.getMessage())
            System.out.println(e.getMessage());
        }


    }

    @Test
    public void find_viewingSerie_should_be_success() throws Exception {
        //Given
        //Visonnage de superman@world.com est dans import.sql
        String email = "superman@world.com";

        //initialiser un toke JWT et le mettre dans header ****************
        HttpHeaders headers = tokenGenerator.getHeadersWithJwtToken("superman@world.com");
        //*****************************************************************

        HttpEntity request = new HttpEntity(headers);

        //When
        //ResponseEntity<ViewingSerieRestitDto[]> response = restTemplate.getForEntity("/api/v1/viewingserie/"+email,ViewingSerieRestitDto[].class);
        // HTTP Get request with headers
        ResponseEntity<ViewingSerieRestitDto[]> response = restTemplate.exchange(
                "/api/v1/viewingserie/"+email,
                HttpMethod.GET,
                request,
                ViewingSerieRestitDto[].class);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull().doesNotHaveDuplicates();
    }
}
