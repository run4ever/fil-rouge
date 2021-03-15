package fr.epita.filrouge.infrastructure.viewingmovie;

import fr.epita.filrouge.domain.entity.common.Category;
import fr.epita.filrouge.domain.entity.common.PublicNotation;
import fr.epita.filrouge.domain.entity.common.Status;
import fr.epita.filrouge.domain.entity.movie.Movie;
import fr.epita.filrouge.domain.entity.movie.MovieRepository;
import fr.epita.filrouge.domain.entity.viewingmovie.ViewingMovie;
import fr.epita.filrouge.domain.entity.viewingmovie.ViewingMovieRepository;
import fr.epita.filrouge.domain.entity.person.AppUser;
import fr.epita.filrouge.domain.entity.person.AppUserRepository;
import fr.epita.filrouge.domain.entity.person.Role;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ViewingMovieTest {

    @Autowired
    private ViewingMovieRepository viewingMovieRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private MovieRepository movieRepository;

    final Movie movie1 = Movie.Builder.aMovie()
            .withId(1L)
            .withImdbId("tt007")
            .withTitle("Movie Title 1")
            .withPublicNotation(new PublicNotation(4.0,1234))
            .withReleaseDate(LocalDate.now())
            .withDuration(115)
            .withCategory(Category.ACTION)
            .build();

    final Movie movie2 = Movie.Builder.aMovie()
            .withImdbId("tt008")
            .withTitle("Movie title 2")
            .withPublicNotation(new PublicNotation(4.0,1234))
            .withReleaseDate(LocalDate.now())
            .withDuration(120)
            .withCategory(Category.COMEDY)
            .build();

    final Movie movie3 = Movie.Builder.aMovie()
            .withImdbId("tt009")
            .withTitle("Movie title 3")
            .withDescription("Movie description 3")
            .withPublicNotation(new PublicNotation(4.0,1234))
            .withReleaseDate(LocalDate.now())
            .withDuration(130)
            .withCategory(Category.BIOGRAPHY)
            .build();


    final AppUser user1 = AppUser.Builder.anAppUser()
            .withId(1L)
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
        appUserRepository.create(user1);
        movieRepository.create(movie1);

        final ViewingMovie wm1 = ViewingMovie.Builder.aViewingMovie()
                .withAppUser(user1)
                .withMovie(movie1)
                .withStatus(Status.TO_WATCH)
                .withLove(true)
                .build();

        //When
        viewingMovieRepository.create(wm1);

        //Then
        assertThat(viewingMovieRepository.findAllViewingMovie()).isNotEmpty();
        assertThat(viewingMovieRepository.findViewingMovieFromUserEmail(user1.getEmail())).isNotEmpty();
    }

    @Test
    public void list_all_viewing_movies_should_success(){
        //Given
        //dans import.sql nous avons injecté 2 lignes dans
        //  insert into movie_viewing values(1000,'2021-03-01','TO_WATCH',4,1);
        //  insert into movie_viewing values(1001,'2021-03-01','TO_WATCH',4,2);
        //When
        List<ViewingMovie> visonnagesMovie = viewingMovieRepository.findAllViewingMovie();
        //Then
        assertThat(visonnagesMovie).isNotNull();
    }

    @Test
    @DisplayName("récupérer le nb de likes")
    public void get_nb_likes_should_success(){
        //Given
        //dans import.sql nous avons injecté 2 lignes dans
        //INSERT INTO public.movie ( actors, average_rating, category, description, duration, image_url, imdb_id, number_of_votes, release_date, title)
        // VALUES ('Harrison Ford, Rutger Hauer, Sean Young, Edward James Olmos', 8.1, 'ACTION', 'A blade runner ...', 117, 'SX300.jpg', 'tt0083658', 695428, '1982-01-01', 'Blade Runner');
        //INSERT INTO public.movie_viewing ( date_modified, status, user_id, movie_id, love) VALUES ('2021-03-01 00:00:00', 'TO_WATCH', 4, 2, true);
        //INSERT INTO public.movie_viewing ( date_modified, status, user_id, movie_id, love) VALUES ('2021-03-01 00:00:00', 'TO_WATCH', 1, 2, true);

        //Then
        assertThat(viewingMovieRepository.countViewingMovieLikesByMovieid("tt0083658")).isEqualTo(2);
    }
}
