package fr.epita.filrouge.application.movie;

import fr.epita.filrouge.domain.entity.common.Category;
import fr.epita.filrouge.domain.entity.common.PublicNotation;
import fr.epita.filrouge.domain.entity.common.Status;
import fr.epita.filrouge.domain.entity.movie.Movie;
import fr.epita.filrouge.domain.entity.movie.ViewingMovie;
import fr.epita.filrouge.domain.entity.movie.ViewingMovieRepository;
import fr.epita.filrouge.domain.entity.person.AppUser;
import fr.epita.filrouge.domain.entity.person.Role;
import fr.epita.filrouge.domain.exception.AlreadyExistingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {ViewingMovieServiceImpl.class})
public class ViewingMovieTest {

    @Autowired
    private ViewingMovieService viewingMovieService;

    @MockBean
    private ViewingMovieRepository viewingMovieRepositoryMock;

    private final static AppUser getAppUserTest() {
        return AppUser.Builder.anAppUser()
                .withFirstname("Alice")
                .withLastname("Tester")
                .withEmail("alice@tester.fr")
                .withBirthdayDate(LocalDate.of(2000, 12, 25))
                .withRole(Role.ROLE_RESP)
                .withPassword("wonderwoman")
                .build();
    }

    private final static Movie getMovieTest() {
        return  Movie.Builder.aMovie()
                .withTitle("Indiana Jones and the Raiders of the Lost Ark")
                .withImdbId("tb001")
                .withPublicNotation(new PublicNotation(4.0,1234))
                .withActors("Harrison Ford, Karen Allen, Paul Freeman, Ronald Lacey")
                .withDuration(115)
                .withCategory(Category.ACTION)
                .withDescription("In 1936, archaeologist and adventurer Indiana Jones is hired by the U.S. government to find the Ark of the Covenant before Adolf Hitler's Nazis can obtain its awesome powers.")
                .withReleaseDate(LocalDate.now())
                .build();
    }


    @Test
    @DisplayName("Création d'un ViewingMovie en échec si Movie est déjà présent dans ViewingMovie pour User")
    public void createViewingMovie_should_fail_when_movie_already_in_viewingMovie() {
        //Given
        AppUser appUser = getAppUserTest();

        Movie movie = getMovieTest();

        ViewingMovie viewingMovie = ViewingMovie.Builder.aViewingMovie()
                                            .withAppUser(appUser)
                                            .withMovie(movie)
                                            .withStatus(Status.TO_WATCH)
                                            .build();

        List<ViewingMovie> listViewingMovie = new ArrayList<>();
        listViewingMovie.add(viewingMovie);

        /** Mock sur create de ViewingMovieRepository (accès à la base de la couche Infra) */
          /* Mockito.doNothing().when(viewingMovieRepositoryMock).create(viewingMovie); */
        when(viewingMovieRepositoryMock.findViewingMovieFromUser(appUser)).thenReturn(listViewingMovie);


        //When
        try {
            viewingMovieService.addMovieToViewingMovie(appUser,movie);
        } catch (final Exception e) {
            assertThat(e).isInstanceOf(AlreadyExistingException.class);
        }

        //Then
        /** Vérifier que create de ViewingMovieRepository n'est jamais appelé dans ce cas */
        verify(viewingMovieRepositoryMock,never()).create(viewingMovie);
    }


    @Test
    @DisplayName("Création d'un ViewingMovie en sucess si Movie n'est pas présent dans ViewingMovie pour User")
    public void createViewingMovie_should_sucess_when_movie_not_exist() {
        //Given
        AppUser appUser = getAppUserTest();

        Movie movie = getMovieTest();

        ViewingMovie viewingMovie = ViewingMovie.Builder.aViewingMovie()
                .withStatus(Status.TO_WATCH) //à la création on met par défaut Movie à regarder
                .withAppUser(appUser)
                .withMovie(movie)
                .build();

        List<ViewingMovie> listViewingMovie = new ArrayList<>();
        listViewingMovie.add(viewingMovie);

        /** Mock sur create de ViewingMovieRepository (accès à la base de la couche Infra) */
        when(viewingMovieRepositoryMock.findViewingMovieFromUser(appUser)).thenReturn(null);


        //When
        viewingMovieService.addMovieToViewingMovie(appUser,movie);

        //Then
        /** Vérifier que create de ViewingMovieRepository appelé 1 fois dans ce cas */
        verify(viewingMovieRepositoryMock,times(1)).create(viewingMovie);
    }


    @Test
    @DisplayName("Création d'un ViewingMovie en sucess ViewingMovie contient autres Movie")
    public void createViewingMovie_should_sucess_when_ViewingMovie_not_null() {
        //Given
        AppUser appUser = getAppUserTest();

        Movie movie = getMovieTest();

        Movie m1 =  Movie.Builder.aMovie()
                .withTitle("Test movie 1")
                .withImdbId("tb999")
                .withPublicNotation(new PublicNotation(4.0,1234))
                .withActors("Harrison Ford, Karen Allen, Paul Freeman, Ronald Lacey")
                .withDuration(115)
                .withCategory(Category.ACTION)
                .withDescription("Bla bla bla")
                .withReleaseDate(LocalDate.now())
                .build();

        ViewingMovie viewingMovie = ViewingMovie.Builder.aViewingMovie()
                .withStatus(Status.TO_WATCH) //à la création on met par défaut Movie à regarder
                .withAppUser(appUser)
                .withMovie(m1)
                .build();

        ViewingMovie vmTocreate = ViewingMovie.Builder.aViewingMovie()
                .withStatus(Status.TO_WATCH) //à la création on met par défaut Movie à regarder
                .withAppUser(appUser)
                .withMovie(movie)
                .build();

        List<ViewingMovie> listViewingMovie = new ArrayList<>();
        listViewingMovie.add(viewingMovie);

        /** Mock sur create de ViewingMovieRepository (accès à la base de la couche Infra) */
        when(viewingMovieRepositoryMock.findViewingMovieFromUser(appUser)).thenReturn(listViewingMovie);


        //When
        viewingMovieService.addMovieToViewingMovie(appUser,movie);

        //Then
        /** Vérifier que create de ViewingMovieRepository appelé 1 fois dans ce cas */
        verify(viewingMovieRepositoryMock,times(1)).create(vmTocreate);
    }



    @Test
    @DisplayName("Appel getViewingMovie délenche un appel de findViewingMovieFromUser de Repository")
    public void getViewingMovie_should_call_findViewingMovieFromUser_1_time() {
        //Given
        AppUser appUser = getAppUserTest();

        /** Mock findByEmail, on recherche AnyString et on return appUser */
        when(viewingMovieRepositoryMock.findViewingMovieFromUser(appUser)).thenReturn(null);

        //When
        viewingMovieService.getViewingMovie(appUser);

        //Then
        /** on doit appeler une fois la méthode findViewingMovieFromUser de Repository */
        verify(viewingMovieRepositoryMock,times(1)).findViewingMovieFromUser(appUser);

    }

}
