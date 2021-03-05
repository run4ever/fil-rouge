package fr.epita.filrouge.application.movie;

import fr.epita.filrouge.application.mapper.MovieDtoMapper;
import fr.epita.filrouge.domain.entity.common.Category;
import fr.epita.filrouge.domain.entity.movie.Movie;
import fr.epita.filrouge.domain.entity.movie.MovieRepository;
import fr.epita.filrouge.domain.entity.movie.MovieRepositoryExternal;
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
    private MovieRepositoryExternal movieRepositoryExternal;

    @MockBean
    private MovieDtoMapper movieDtoMapper;

    @MockBean
    private MovieRepository movieRepositoryMock;

    @Test
    public void create_not_existing_movie_should_succeed(){
        //Given
        MovieDto m1 = MovieDto.Builder.aMovieDto()
                .withImdbId("apiM001")
                .withCategory(Category.ACTION)
                .withDuration(120)
                .withTitle("Movie title 1")
                .withReleaseDate(LocalDate.of(2000,10,01))
                .build();

        Movie m1e = Movie.Builder.aMovie()
                .withImdbId("apiM001")
                .withCategory(Category.ACTION)
                .withDuration(120)
                .withTitle("Movie title 1")
                .withReleaseDate(LocalDate.of(2000,10,01))
                .build();

        // Mocks
        when(movieDtoMapper.mapDtoToDomain(m1)).thenReturn(m1e);
        when(movieDtoMapper.mapDomainToDto(m1e)).thenReturn(m1);
        when(movieRepositoryMock.findMovieFromApiId("apiM001")).thenReturn(null);

        //When
        movieService.createMovieService(m1);

        //Then
        verify(movieRepositoryMock,times(1)).create(movieDtoMapper.mapDtoToDomain(m1));
    }

    @Test
    public void create_existing_movie_should_crash(){
        //Given
        MovieDto m2 = MovieDto.Builder.aMovieDto()
                .withImdbId("apiM002")
                .withCategory(Category.COMEDY)
                .withDuration(120)
                .withTitle("Movie title 2")
                .withReleaseDate(LocalDate.of(2000,10,02))
                .build();

        Movie m2e = Movie.Builder.aMovie()
                .withImdbId("apiM002")
                .withCategory(Category.COMEDY)
                .withDuration(120)
                .withTitle("Movie title 2")
                .withReleaseDate(LocalDate.of(2000,10,02))
                .build();

        // Mocks
        when(movieDtoMapper.mapDtoToDomain(m2)).thenReturn(m2e);
        when(movieDtoMapper.mapDomainToDto(m2e)).thenReturn(m2);
        when(movieRepositoryMock.findMovieFromApiId("apiM002"))
                .thenReturn(m2e);

        //When
        try{
            movieService.createMovieService(m2);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(AlreadyExistingException.class);
        }

        //Then
        verify(movieRepositoryMock,never()).create(movieDtoMapper.mapDtoToDomain(m2));
    }

    @Test
    public void find_movie_should_success(){
        //Given
        MovieDto m3 = MovieDto.Builder.aMovieDto()
                .withImdbId("apiM003")
                .withCategory(Category.COMEDY)
                .withDuration(120)
                .withTitle("Movie title 3")
                .withReleaseDate(LocalDate.of(2000,10,02))
                .build();

        Movie m3e = Movie.Builder.aMovie()
                .withImdbId("apiM003")
                .withCategory(Category.COMEDY)
                .withDuration(120)
                .withTitle("Movie title 3")
                .withReleaseDate(LocalDate.of(2000,10,02))
                .build();

        when(movieDtoMapper.mapDtoToDomain(m3)).thenReturn(m3e);
        when(movieDtoMapper.mapDomainToDto(m3e)).thenReturn(m3);
        when(movieRepositoryMock.findMovieFromApiId("apiM002"))
                .thenReturn(m3e);

        //When
        final MovieDto result = movieService.getOneMovieService("apiM002");

        //Then
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Movie title 3");

    }


}
