package fr.epita.filrouge.application.viewingmovie;

import fr.epita.filrouge.application.mapper.AppUserDtoMapper;
import fr.epita.filrouge.application.mapper.MovieDtoMapper;
import fr.epita.filrouge.application.mapper.ViewingMovieDtoMapper;
import fr.epita.filrouge.application.movie.MovieDto;
import fr.epita.filrouge.application.movie.MovieService;
import fr.epita.filrouge.application.person.AppUserDto;
import fr.epita.filrouge.domain.entity.common.Category;
import fr.epita.filrouge.domain.entity.common.PublicNotation;
import fr.epita.filrouge.domain.entity.common.Status;
import fr.epita.filrouge.domain.entity.movie.Movie;
import fr.epita.filrouge.domain.entity.viewingmovie.ViewingMovie;
import fr.epita.filrouge.domain.entity.viewingmovie.ViewingMovieRepository;
import fr.epita.filrouge.domain.entity.person.AppUser;
import fr.epita.filrouge.domain.entity.person.Role;
import fr.epita.filrouge.domain.exception.AlreadyExistingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
    private MovieService movieService;

    @MockBean
    private ViewingMovieDtoMapper viewingMovieDtoMapper;

    @MockBean
    private AppUserDtoMapper appUserDtoMapper;

    @MockBean
    private MovieDtoMapper movieDtoMapper;

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

    private final static AppUserDto getAppUserDtoTest() {
        return AppUserDto.Builder.anAppUserDto()
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
                .withDescription("In 1936, archaeologist and adventurer Indiana Jones is hired by the U.S. government to find the Ark of the Covenant...")
                .withReleaseDate(LocalDate.now())
                .build();
    }

    private final static MovieDto getMovieDtoTest() {
        return  MovieDto.Builder.aMovieDto()
                .withTitle("Indiana Jones and the Raiders of the Lost Ark")
                .withImdbId("tb001")
                .withActors("Harrison Ford, Karen Allen, Paul Freeman, Ronald Lacey")
                .withDuration(115)
                .withCategory(Category.ACTION)
                .withDescription("In 1936, archaeologist and adventurer Indiana Jones is hired by the U.S. government to find the Ark of the Covenant...")
                .withReleaseDate(LocalDate.now())
                .build();
    }

    @Test
    @DisplayName("Création d'un ViewingMovie en échec si Movie est déjà présent dans ViewingMovie pour User")
    public void createViewingMovie_should_fail_when_movie_already_in_viewingMovie() {
        //Given
        AppUser appUser = getAppUserTest();
        Movie movie = getMovieTest();

        ViewingMovieCreateDto viewingMovieCreateDto = ViewingMovieCreateDto.Builder.aViewingMovieCreateDto()
                .withEmail(appUser.getEmail())
                .withImdbId(movie.getImdbId())
                .withStatus(Status.TO_WATCH)
                .build();

        ViewingMovie viewingMovie = ViewingMovie.Builder.aViewingMovie()
                .withAppUser(appUser)
                .withMovie(movie)
                .withStatus(Status.TO_WATCH)
                .build();

        List<ViewingMovie> listViewingMovie = new ArrayList<>();
        listViewingMovie.add(viewingMovie);

        /** Mock sur create de ViewingMovieRepository (accès à la base de la couche Infra) */
        when(viewingMovieRepositoryMock.findViewingMovieFromUser(appUser)).thenReturn(listViewingMovie);

        //When
        try {
            viewingMovieService.addMovieToViewingMovie(viewingMovieCreateDto);
        } catch (final Exception e) {
            assertThat(e).isInstanceOf(AlreadyExistingException.class);
        }

        //Then
        /** Vérifier que create de ViewingMovieRepository n'est jamais appelé dans ce cas */
        verify(viewingMovieRepositoryMock,never()).create(viewingMovie);
    }

    @Test
    @DisplayName("Création d'un ViewingMovie en sucess si Movie n'est pas présent dans ViewingMovie pour User")
    public void createViewingMovie_should_sucess_when_movie_not_in_user_list() {
        //Given
        AppUser appUser = getAppUserTest();
        Movie movie = getMovieTest();

        ViewingMovieCreateDto viewingMovieCreateDto = ViewingMovieCreateDto.Builder.aViewingMovieCreateDto()
                .withEmail(appUser.getEmail())
                .withImdbId(movie.getImdbId())
                .withStatus(Status.TO_WATCH)
                .build();

        final ViewingMovie viewingMovie = viewingMovieDtoMapper.mapCreateDtoToDomain(viewingMovieCreateDto);

        List<ViewingMovie> listViewingMovie = new ArrayList<>();
        listViewingMovie.add(viewingMovie);

        /** Mock sur create de ViewingMovieRepository (accès à la base de la couche Infra) */
        when(viewingMovieRepositoryMock
                .findViewingMovieFromUserEmailAndMovieId(
                        viewingMovieCreateDto.getEmail(),
                        viewingMovieCreateDto.getImdbId())
        ).thenReturn(null);

        //When
        viewingMovieService.addMovieToViewingMovie(viewingMovieCreateDto);

        //Then
        /** Vérifier que create de ViewingMovieRepository appelé 1 fois dans ce cas */
        verify(viewingMovieRepositoryMock,times(1)).create(viewingMovie);
    }

    @Test
    @DisplayName("Création d'un ViewingMovie en sucess dans une liste contenant d'autres viewing movie")
    public void createViewingMovie_should_sucess_when_ViewingMovieList_not_null() {
        //Given
        AppUser appUser = getAppUserTest();
        AppUserDto appUserDto = getAppUserDtoTest();
        Movie movie = getMovieTest();
        MovieDto movieDto =getMovieDtoTest();

        Movie m1 =  Movie.Builder.aMovie()
                .withTitle("Indiana Jones and the Raiders of the Lost Ark")
                .withImdbId("tb999")
                .withPublicNotation(new PublicNotation(4.0,1234))
                .withActors("Harrison Ford, Karen Allen, Paul Freeman, Ronald Lacey")
                .withDuration(115)
                .withCategory(Category.ACTION)
                .withDescription("In 1936, archaeologist and adventurer Indiana Jones is hired by the U.S. government to find the Ark of the Covenant...")
                .withReleaseDate(LocalDate.now())
                .build();

        ViewingMovieCreateDto viewingMovieCreateDto = ViewingMovieCreateDto.Builder.aViewingMovieCreateDto()
                .withEmail(appUser.getEmail())
                .withImdbId(movie.getImdbId())
                .withStatus(Status.TO_WATCH)
                .build();

        final ViewingMovie viewingMovie = viewingMovieDtoMapper.mapCreateDtoToDomain(viewingMovieCreateDto);

        List<ViewingMovie> listViewingMovie = new ArrayList<>();
        listViewingMovie.add(viewingMovie);

        /** Mock sur create de ViewingMovieRepository (accès à la base de la couche Infra) */
        when(viewingMovieRepositoryMock.findViewingMovieFromUser(appUser)).thenReturn(listViewingMovie);
        when(appUserDtoMapper.mapDtoToDomain(appUserDto)).thenReturn(appUser);
        when(movieDtoMapper.mapDtoToDomain(movieDto)).thenReturn(movie);

        //When
        viewingMovieService.addMovieToViewingMovie(viewingMovieCreateDto);

        //Then
        /** Vérifier que create de ViewingMovieRepository appelé 1 fois dans ce cas */
        verify(viewingMovieRepositoryMock,times(1)).create(viewingMovie);
    }


    @Test
    @DisplayName("Appel getViewingMovie déclenche un appel de findViewingMovieFromUser de Repository")
    public void getViewingMovie_should_call_findViewingMovieFromUser_1_time() {
        //Given
        AppUser appUser = getAppUserTest();
        AppUserDto appUserDto = getAppUserDtoTest();

        when(viewingMovieRepositoryMock.findViewingMovieFromUser(appUser)).thenReturn(null);
        when(appUserDtoMapper.mapDtoToDomain(appUserDto)).thenReturn(appUser);

        //When
        viewingMovieService.getViewingMovie(appUserDto);

        //Then
        /** on doit appeler une fois la méthode findViewingMovieFromUser de Repository */
        /** appUser à la place any() test en éhec */
        verify(viewingMovieRepositoryMock,times(1)).findViewingMovieFromUser(appUser);
    }

    @Test
    @DisplayName("Nombre de likes d'un movie")
    public void count_movie_nb_likes_should_success(){
        //Given
        AppUser appUser = getAppUserTest();
        Movie movie = getMovieTest();
        ViewingMovie viewingMovie = ViewingMovie.Builder.aViewingMovie()
                .withAppUser(appUser)
                .withMovie(movie)
                .withStatus(Status.WATCHED)
                .withLove(true)
                .build();

        //When
        viewingMovieService.searchViewingMovieNbLikes(movie.getImdbId());

        //Then
        verify(viewingMovieRepositoryMock,times(1)).countViewingMovieLikesByMovieid(movie.getImdbId());
    }

}
