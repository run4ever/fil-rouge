package fr.epita.filrouge.exposition.controller;


import fr.epita.filrouge.application.serie.SerieDto;
import fr.epita.filrouge.domain.entity.common.Category;
import fr.epita.filrouge.domain.entity.serie.StatusSerie;
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
public class SerieResourceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void add_serie_should_be_success() throws Exception {
        //Given
        SerieDto serieDto = SerieDto.Builder.aSerieDto()
                .withImdbId("tt99test999")
                .withTitle("Monica")
                .withStartYear(1980)
                .withEndYear(1990)
                .withNumberOfSeason(10)
                .withNumberOfEpisode(150)
                .withCategory(Category.ANIMATION)
                .withDescription("Serie Monica")
                .withStatusSerie(StatusSerie.FINISH)
                .build();

        //When
        ResponseEntity<SerieDto> response = restTemplate.postForEntity("/api/v1/serie/create",serieDto, SerieDto.class);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }


    @Test
    public void find_existing_serie_should_be_success() throws Exception {
        //Given
        // tt00test000 est dans import.sql
        String idSerie = "tt00test000";

        //When
        ResponseEntity<SerieDto> response = restTemplate.getForEntity("/api/v1/serie/"+idSerie,SerieDto.class);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getImdbId()).isEqualTo(idSerie);
        assertThat(response.getBody().getTitle()).isEqualTo("friends");

    }

    @Test
    public void serie_not_found_should_return_code404() throws Exception {
        //Given
        // cette idSerie ttXXXXXXXX n'existe pas
        String idSerie = "ttXXXXXXXX";

        //When
        ResponseEntity<SerieDto> response = restTemplate.getForEntity("/api/v1/serie/"+idSerie,SerieDto.class);

        //Then
        //** TODO on devait avoir NOT_FOUND
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
       // assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Test
    public void list_Serie_should_be_success() throws Exception {
        //Given

        //When
        ResponseEntity<SerieDto[]> response = restTemplate.getForEntity("/api/v1/movie/list", SerieDto[].class);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        //résultat ne doit pas être nul et pas de duplication Movie
        assertThat(response.getBody()).isNotNull().doesNotHaveDuplicates();


    }

}
