package fr.epita.filrouge.application.viewingserie;


import fr.epita.filrouge.application.mapper.ViewingSerieDtoMapper;
import fr.epita.filrouge.application.person.AppUserDto;
import fr.epita.filrouge.application.serie.SerieDto;
import fr.epita.filrouge.application.serie.SerieService;
import fr.epita.filrouge.domain.entity.common.PublicNotation;
import fr.epita.filrouge.domain.entity.person.AppUser;
import fr.epita.filrouge.domain.entity.person.AppUserRepository;
import fr.epita.filrouge.domain.entity.person.Role;
import fr.epita.filrouge.domain.entity.serie.Serie;
import fr.epita.filrouge.domain.entity.viewingserie.ViewingSerie;
import fr.epita.filrouge.domain.entity.viewingserie.ViewingSerieRepository;
import fr.epita.filrouge.domain.exception.AlreadyExistingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.epita.filrouge.domain.entity.common.Status;

import static org.mockito.Mockito.*;


@SpringBootTest(classes = {ViewingSerieServiceImpl.class})
public class ViewingSerieTest {

    @Autowired
    private ViewingSerieService viewingSerieService;

    @MockBean
    private ViewingSerieRepository viewingSerieRepositoryMock;

    @MockBean
    private AppUserRepository appUserRepositoryMock;

    @MockBean
    private ViewingSerieDtoMapper viewingSerieDtoMapper;

    @MockBean
    private SerieService serieService;

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

    private final static Serie getSerieTest() {
        return Serie.Builder.aSerie()
                .withImdbId("tt00serie001")
                .withTitle("serie 001")
                .withPublicNotation(new PublicNotation(5D,250))
                .build();
    }

    private final static SerieDto getSerieDtoTest() {
        return SerieDto.Builder.aSerieDto()
                .withImdbId("tt00serie001")
                .withTitle("serie 001")
                .withAverageRating(5D)
                .withNumberOfVotes(250)
                .build();
    }

    @Test
    public void create_viewingSerie_should_fail_Serie_Already_exsting_in_viewingSerie(){
        //Given
        AppUser appUser = getAppUserTest();
        Serie serie = getSerieTest();
        ViewingSerieCreateDto viewingSerieCreateDto = ViewingSerieCreateDto.Builder.aViewingSerieCreateDto()
                .withEmail(appUser.getEmail())
                .withImdbId(serie.getImdbId())
                .withStatus(Status.TO_WATCH)
                .withCurrentSeason(2)
                .withCurrentEpisode(14)
                .build();

        ViewingSerie viewingSerie = ViewingSerie.Builder.aViewingSerie()
                .withAppUser(appUser)
                .withSerie(serie)
                .withStatus(Status.TO_WATCH)
                .withCurrentSeason(2)
                .withCurrentEpisode(14)
                .build();

        List<ViewingSerie> viewingSerieList = new ArrayList<>();
        viewingSerieList.add(viewingSerie);
        /** Mock ViewingSerieRepository */
        when(viewingSerieRepositoryMock.findByIdUserAndIdSerie(appUser.getEmail(),serie.getImdbId())).thenReturn(viewingSerie);

        //When
        try {
            viewingSerieService.create(viewingSerieCreateDto);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(AlreadyExistingException.class);
        }

        //Then
        /** vérifier que create de repository n'est jamais appelé dans ce cas */
        verify(viewingSerieRepositoryMock,never()).create(viewingSerie);
    }

    @Test
    public void create_viewingSerie_should_sucess_when_serie_not_in_viewingSerie() {
        //Given
        AppUser appUser = getAppUserTest();
        Serie serie = getSerieTest();
        ViewingSerieCreateDto viewingSerieCreateDto = ViewingSerieCreateDto.Builder.aViewingSerieCreateDto()
                .withEmail(appUser.getEmail())
                .withImdbId(serie.getImdbId())
                .withStatus(Status.TO_WATCH)
                .withCurrentSeason(2)
                .withCurrentEpisode(14)
                .build();

        ViewingSerie viewingSerie = viewingSerieDtoMapper.mapToDomainCreate(viewingSerieCreateDto);

        List<ViewingSerie> viewingSerieList = new ArrayList<>();
        viewingSerieList.add(viewingSerie);
        /** Mock ViewingSerieRepository */
        when(viewingSerieRepositoryMock.findByIdUserAndIdSerie(appUser.getEmail(),serie.getImdbId())).thenReturn(null);

        //When
        viewingSerieService.create(viewingSerieCreateDto);

        //Then
        /** vérifier que ViewingSerieReposistory.create est appelé une fois */
        verify(viewingSerieRepositoryMock,times(1)).create(viewingSerie);
    }

    @Test
    public void find_existing_viewingSerie_should_call_1_time_findByIdUserAndIdSerie() {
        //Given
        AppUser appUser = getAppUserTest();
        Serie serie = getSerieTest();
        /** Mock */
        when(viewingSerieRepositoryMock.findByIdUserAndIdSerie(appUser.getEmail(),serie.getImdbId())).thenReturn(null);
        //When
        viewingSerieService.findViewing(appUser.getEmail(), serie.getImdbId());

        //Then
        /** véririfier viewingSerieRepository.findByIdUserAndIdSerie est appelé 1 fois */
        verify(viewingSerieRepositoryMock,times(1)).findByIdUserAndIdSerie(appUser.getEmail(),serie.getImdbId());
    }


    //List<ViewingSerieRestitDto> findByUserAllVievingSerieDto(String email);
    @Test
    public void find_list_viewingSerie_for_existing_user_should_be_sucess() {
        //Given
        AppUser appUser = getAppUserTest();
        Serie serie = getSerieTest();
        ViewingSerie viewingSerie = ViewingSerie.Builder.aViewingSerie()
                .withAppUser(appUser)
                .withSerie(serie)
                .withStatus(Status.TO_WATCH)
                .withCurrentSeason(2)
                .withCurrentEpisode(14)
                .build();

        List<ViewingSerie> viewingSerieList = new ArrayList<>();
        viewingSerieList.add(viewingSerie);

        /**  Mock sur AppUser findByEmail */
        when(appUserRepositoryMock.findbyEmail(appUser.getEmail())).thenReturn(appUser);
        when(viewingSerieRepositoryMock.findallViewingSerieByUser(appUser.getEmail())).thenReturn(viewingSerieList);

        //When
        viewingSerieService.findByUserAllVievingSerieDto(appUser.getEmail());

        //Then
        /** vérifier viewingSerieRepository.findallViewingSerieByUse est appelé 1 fois  */
        verify(viewingSerieRepositoryMock,times(1)).findallViewingSerieByUser(appUser.getEmail());

    }

    @Test
    public void count_serie_nb_likes_should_success(){
        //Given
        AppUser appUser = getAppUserTest();
        Serie serie = getSerieTest();
        ViewingSerie viewingSerie = ViewingSerie.Builder.aViewingSerie()
                .withAppUser(appUser)
                .withSerie(serie)
                .withStatus(Status.WATCHED)
                .withCurrentSeason(2)
                .withCurrentEpisode(14)
                .withLove(true)
                .build();

        //When
        viewingSerieService.searchViewingSerieNbLikes(serie.getImdbId());

        //Then
        verify(viewingSerieRepositoryMock,times(1)).countViewingSerieLikesBySerieid(serie.getImdbId());
    }

}
