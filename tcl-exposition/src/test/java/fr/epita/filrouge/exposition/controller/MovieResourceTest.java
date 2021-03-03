package fr.epita.filrouge.exposition.controller;

import fr.epita.filrouge.application.movie.MovieDto;
import fr.epita.filrouge.domain.entity.common.Category;

import org.junit.jupiter.api.Disabled;
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
public class MovieResourceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Disabled("désactiver tempo à cause de la sécurité JWT => TODO")
    public void add_Movie_should_be_success() throws Exception {
        //Given
        MovieDto movieDto = MovieDto.Builder.aMovieDto()
                .withImdbId("TB00TEST")
                .withTitle("Indiana Jones and the Raiders of the Lost Ark")
                .withAverageRating(5D)
                .withNumberOfVotes(200)
                .withActors("Harrison Ford, Karen Allen, Paul Freeman, Ronald Lacey")
                .withDuration(115)
                .withCategory(Category.ACTION)
                .withDescription("In 1936, archaeologist and adventurer Indiana Jones is hired by the U.S. government to find the Ark of the Covenant before Nazis can obtain its awesome powers.")
                .withReleaseDate(LocalDate.now())
                .build();

        //When
        ResponseEntity<MovieDto> response = restTemplate.postForEntity("/api/v1/movie/add",movieDto, MovieDto.class);


        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void found_existing_Movie_should_be_success() throws Exception {
        //Given
        //tt1856101 est dans import.sql
        String idMovie = "tt1856101";

        //When
        ResponseEntity<MovieDto> response = restTemplate.getForEntity("/api/v1/movie/"+idMovie, MovieDto.class);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getTitle()).isEqualTo("Blade Runner 2049");
        assertThat(response.getBody().getImdbId()).isEqualTo(idMovie);
    }

    @Test
    public void list_Movie_should_be_success() throws Exception {
        //Given

        //When
        ResponseEntity<MovieDto[]> response = restTemplate.getForEntity("/api/v1/movie/list", MovieDto[].class);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        //résultat ne doit pas être nul et pas de duplication Movie
        assertThat(response.getBody()).isNotNull().doesNotHaveDuplicates();

    }
}
