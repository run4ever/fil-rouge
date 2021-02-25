package fr.epita.filrouge.application.movie;

import fr.epita.filrouge.domain.entity.common.Category;
import fr.epita.filrouge.domain.entity.movie.Movie;
import fr.epita.filrouge.domain.entity.movie.MovieRepository;
import fr.epita.filrouge.domain.exception.AlreadyExistingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {MovieServiceImpl.class})
public class MovieTest {

    @Autowired
    private MovieService movieService;

    @MockBean
    private MovieRepository movieRepositoryMock;

    @Test
    public void create_not_existing_movie_should_succeed(){
        //Given
        Movie m1 = Movie.Builder.aMovie()
                .withImdbId("apiM001")
                .withCategory(Category.ACTION)
                .withDuration(120)
                .withTitle("Movie title 1")
                .withReleaseDate(LocalDate.of(2000,10,01))
                .build();

        when(movieRepositoryMock.findMovieFromApiId("apiM001")).thenReturn(null);

        //When
        movieService.createMovieService(m1);

        //Then
        verify(movieRepositoryMock,times(1)).create(m1);
    }

    @Test
    public void create_existing_movie_should_crash(){
        //Given
        Movie m2 = Movie.Builder.aMovie()
                .withImdbId("apiM002")
                .withCategory(Category.COMEDY)
                .withDuration(120)
                .withTitle("Movie title 2")
                .withReleaseDate(LocalDate.of(2000,10,02))
                .build();

        when(movieRepositoryMock.findMovieFromApiId("apiM002")).thenReturn(m2);

        //When
        try{
            movieService.createMovieService(m2);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(AlreadyExistingException.class);
        }

        //Then
        verify(movieRepositoryMock,never()).create(m2);
    }

    @Test
    public void find_movie_should_success(){
        //Given
        Movie m3 = Movie.Builder.aMovie()
                .withImdbId("apiM003")
                .withCategory(Category.COMEDY)
                .withDuration(120)
                .withTitle("Movie title 3")
                .withReleaseDate(LocalDate.of(2000,10,02))
                .build();

        when(movieRepositoryMock.findMovieFromApiId("apiM002")).thenReturn(m3);

        //When
        final Movie result = movieService.getOneMovieService("apiM002");

        //Then
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Movie title 3");

    }


}
