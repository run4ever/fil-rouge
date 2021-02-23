package fr.epita.filrouge.infrastructure.movie;

import fr.epita.filrouge.domain.entity.common.Category;
import fr.epita.filrouge.domain.entity.common.PublicNotation;
import fr.epita.filrouge.domain.entity.common.Status;
import fr.epita.filrouge.domain.entity.movie.Movie;
import fr.epita.filrouge.domain.entity.movie.MovieRepository;
import fr.epita.filrouge.domain.entity.movie.ViewingMovie;
import fr.epita.filrouge.domain.entity.movie.ViewingMovieRepository;
import fr.epita.filrouge.domain.entity.person.AppUser;
import fr.epita.filrouge.domain.entity.person.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MovieTests {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ViewingMovieRepository viewingMovieRepository;

    final Movie movie1 = Movie.Builder.aMovie()
            .withTitle("Indiana Jones and the Raiders of the Lost Ark")
            .withPublicNotation(new PublicNotation(4.0,1234))
            .withActors("Harrison Ford, Karen Allen, Paul Freeman, Ronald Lacey")
            .withDuration(115)
            .withCategory(Category.ACTION)
            .withDescription("In 1936, archaeologist and adventurer Indiana Jones is hired by the U.S. government to find the Ark of the Covenant before Adolf Hitler's Nazis can obtain its awesome powers.")
            .withReleaseDate(LocalDate.now())
            .build();

    final AppUser user1 = AppUser.Builder.anAppUser()
            .withEmail("fabien.laurette@gmail.com")
            .withFirstname("Fabien")
            .withLastname("Laurette")
            .withBirthdayDate(LocalDate.now())
            .withPassword("admin")
            .withRole(Role.ROLE_ADMIN)
            .build();

    @Test
    public void add_new_movie_should_success()  {
        //Given
            //movie1

        // When
        movieRepository.create(movie1);

        // Then
        assertThat(movieRepository.findMovieFromTitle(movie1.getTitle())).isNotNull();
    }

    @Test
    public void add_viewing_movie_should_success(){
        //Given
        final ViewingMovie wm1 = ViewingMovie.Builder.aViewingMovie()
                .withAppUser(user1)
                .withMovie(movie1)
                .withStatus(Status.TO_WATCH)
                .build();

        //When
        viewingMovieRepository.create(wm1);

        //Then
        assertThat(viewingMovieRepository.findAllViewingMovie()).isNotEmpty();
        assertThat(viewingMovieRepository.findViewingMovieFromUserLastname(user1.getLastname())).isNotEmpty();
    }


}
