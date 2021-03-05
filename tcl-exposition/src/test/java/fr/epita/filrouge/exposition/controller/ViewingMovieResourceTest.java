package fr.epita.filrouge.exposition.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.epita.filrouge.application.person.AppUserDto;
import fr.epita.filrouge.application.viewingmovie.ViewingMovieCreateDto;
import fr.epita.filrouge.application.viewingmovie.ViewingMovieRestitDto;
import fr.epita.filrouge.application.viewingmovie.ViewingMovieService;
import fr.epita.filrouge.domain.entity.common.Status;
import fr.epita.filrouge.exposition.controller.common.TokenGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.setAllowComparingPrivateFields;
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

    @Autowired
    private TokenGenerator tokenGenerator;

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

        //initialiser un toke JWT et le mettre dans header ****************
        HttpHeaders headers = tokenGenerator.getHeadersWithJwtToken("arnaud@tcl.com");
        //*****************************************************************
        HttpEntity<ViewingMovieCreateDto> request = new HttpEntity<>(viewingMovieCreateDto,headers);

        //When
        ResponseEntity<ViewingMovieCreateDto> response = restTemplate.postForEntity("/api/v1/viewing-movie/create",request, ViewingMovieCreateDto.class);

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

        //initialiser un toke JWT et le mettre dans header ****************
        HttpHeaders headers = tokenGenerator.getHeadersWithJwtToken("fabien@tcl.com");
        //*****************************************************************
        HttpEntity<ViewingMovieCreateDto> request = new HttpEntity<>(viewingMovieCreateDto,headers);

        final Integer nbViewingMovieBefore = viewingMovieService.getViewingMovieByUserEmail("fabien@tcl.com").size();

        //When
        ResponseEntity<ViewingMovieCreateDto> response = restTemplate.postForEntity("/api/v1/viewing-movie/create",request, ViewingMovieCreateDto.class);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(viewingMovieService.getViewingMovieByUserEmail("fabien@tcl.com").size()).isEqualTo(nbViewingMovieBefore);

    }

    @Test
    public void list_one_user_vms_should_success(){
        //Given
        String userEmail = "fabien@tcl.com";

        //initialiser un toke JWT et le mettre dans header ****************
        HttpHeaders headers = tokenGenerator.getHeadersWithJwtToken("fabien@tcl.com");
        //*****************************************************************
        HttpEntity<String> request = new HttpEntity<>(userEmail,headers);

        //When
        ResponseEntity<ViewingMovieRestitDto[]> response = restTemplate.postForEntity("/api/v1/viewing-movie/list", request, ViewingMovieRestitDto[].class);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull().doesNotHaveDuplicates();

        ViewingMovieRestitDto[] results = response.getBody();

        for (int i = 0; i < results.length; i++) {
            System.out.println(results[i].getMovieDto().getImdbId());
        }
    }

    @Test
    public void update_existing_vm_should_success() throws Exception {
        //Given
        ViewingMovieCreateDto updatedViewingMovie = ViewingMovieCreateDto.Builder.aViewingMovieCreateDto()
                .withEmail("fabien@tcl.com")
                .withImdbId("tt1856101")
                .withStatus(Status.WATCHED)
                .build();

        //initialiser un toke JWT et le mettre dans header ****************
        HttpHeaders headers = tokenGenerator.getHeadersWithJwtToken("fabien@tcl.com");
        //*****************************************************************

        // When
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/viewing-movie/update")
                .headers(headers)
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

        //initialiser un toke JWT et le mettre dans header ****************
        HttpHeaders headers = tokenGenerator.getHeadersWithJwtToken("fabien@tcl.com");
        //*****************************************************************

        // When
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/viewing-movie/delete")
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(vmToDelete)))
                 //Then
                .andExpect(status().isOk());
    }

}
