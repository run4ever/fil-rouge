package fr.epita.filrouge.infrastructure.movie;

import fr.epita.filrouge.domain.entity.common.Category;
import fr.epita.filrouge.domain.entity.common.PublicNotation;
import fr.epita.filrouge.domain.entity.common.Status;
import fr.epita.filrouge.domain.entity.movie.Movie;
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
public class ViewingMovieTest {

    @Autowired
    private ViewingMovieRepository viewingMovieRepository;

    final Movie movie1 = Movie.Builder.aMovie()
            .withTitle("Indiana Jones and the Raiders of the Lost Ark")
            .withPublicNotation(new PublicNotation(4.0,1234))
            .withReleaseDate(LocalDate.now())
            .withDuration(115)
            .withCategory(Category.ACTION)
            .build();

    final Movie movie2 = Movie.Builder.aMovie()
            .withTitle("Movie title 2")
            .withPublicNotation(new PublicNotation(4.0,1234))
            .withReleaseDate(LocalDate.now())
            .withDuration(120)
            .withCategory(Category.COMEDY)
            .build();

    final AppUser user1 = AppUser.Builder.anAppUser()
            .withEmail("user@gmail.com")
            .withFirstname("Alain")
            .withLastname("Proviste")
            .withBirthdayDate(LocalDate.now())
            .withPassword("admin")
            .withRole(Role.ROLE_ADMIN)
            .build();

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

    @Test
    public void list_all_viewing_movies_should_success(){
        //Given
        final ViewingMovie wm1 = ViewingMovie.Builder.aViewingMovie()
                .withAppUser(user1)
                .withMovie(movie1)
                .withStatus(Status.TO_WATCH)
                .build();

        final ViewingMovie wm2 = ViewingMovie.Builder.aViewingMovie()
                .withAppUser(user1)
                .withMovie(movie2)
                .withStatus(Status.TO_WATCH)
                .build();

        int wmNb = viewingMovieRepository.findAllViewingMovie().size();

        //When
        viewingMovieRepository.create(wm1);
        viewingMovieRepository.create(wm2);
        //Then
        assertThat(viewingMovieRepository.findAllViewingMovie()).size().isEqualTo(wmNb+2);

    }

}
