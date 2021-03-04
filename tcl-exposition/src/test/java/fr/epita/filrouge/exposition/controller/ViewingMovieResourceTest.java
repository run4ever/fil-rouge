package fr.epita.filrouge.exposition.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.epita.filrouge.application.viewingmovie.ViewingMovieCreateDto;
import fr.epita.filrouge.application.viewingmovie.ViewingMovieRestitDto;
import fr.epita.filrouge.application.viewingmovie.ViewingMovieService;
import fr.epita.filrouge.domain.entity.common.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ViewingMovieResourceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ViewingMovieService viewingMovieService;

    private final ObjectMapper mapper = new ObjectMapper();


    @Test
    public void create_viewing_movie_should_success(){
        //Given
        ViewingMovieCreateDto viewingMovieCreateDto = ViewingMovieCreateDto.Builder.aViewingMovieCreateDto()
                .withEmail("arnaud@tcl.com")
                .withImdbId("tt1856101")
                .withStatus(Status.TO_WATCH)
                .build();

        final int nbViewingMovieBefore = viewingMovieService.getViewingMovieByUserEmail("arnaud@tcl.com").size();

        //When
        ResponseEntity<ViewingMovieCreateDto> response = restTemplate.postForEntity("/api/v1/viewing-movie/create",viewingMovieCreateDto, ViewingMovieCreateDto.class);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(viewingMovieService.getViewingMovieByUserEmail("arnaud@tcl.com").size()).isEqualTo(nbViewingMovieBefore+1);
    }

    @Test
    public void create_already_existing_vm_should_crash(){
        //Given
        ViewingMovieCreateDto viewingMovieCreateDto = ViewingMovieCreateDto.Builder.aViewingMovieCreateDto()
                .withEmail("fabien@tcl.com")
                .withImdbId("tt1856101")
                .withStatus(Status.TO_WATCH)
                .build();

        final Integer nbViewingMovieBefore = viewingMovieService.getViewingMovieByUserEmail("fabien@tcl.com").size();

        //When
        ResponseEntity<ViewingMovieCreateDto> response = restTemplate.postForEntity("/api/v1/viewing-movie/create",viewingMovieCreateDto, ViewingMovieCreateDto.class);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(viewingMovieService.getViewingMovieByUserEmail("fabien@tcl.com").size()).isEqualTo(nbViewingMovieBefore);

    }

    @Test
    public void list_one_user_vms_should_success(){
        //Given
        String userEmail = "fabien@tcl.com";

        //When
        ResponseEntity<ViewingMovieRestitDto[]> response = restTemplate.postForEntity("/api/v1/viewing-movie/list", userEmail, ViewingMovieRestitDto[].class);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull().doesNotHaveDuplicates();
    }

    @Test
    public void update_existing_vm_should_success() throws Exception {
        //Given
        ViewingMovieCreateDto updatedViewingMovie = ViewingMovieCreateDto.Builder.aViewingMovieCreateDto()
                .withEmail("fabien@tcl.com")
                .withImdbId("tt1856101")
                .withStatus(Status.WATCHED)
                .build();

        // When
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/viewing-movie/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updatedViewingMovie)))
                // Then
                .andExpect(status().isCreated());
    }

    @Test
    public void delete_one_user_vm_should_success() throws Exception {
        //Given
        ViewingMovieCreateDto vmToDelete = ViewingMovieCreateDto.Builder.aViewingMovieCreateDto()
                .withEmail("fabien@tcl.com")
                .withImdbId("tt0083658")
                .withStatus(Status.TO_WATCH)
                .build();

        // When
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/viewing-movie/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(vmToDelete)))
                 //Then
                .andExpect(status().isOk());
    }

}
