package fr.epita.filrouge.application.serie;


import fr.epita.filrouge.application.mapper.AppUserDtoMapper;
import fr.epita.filrouge.application.mapper.MapperViewingSerieDto;
import fr.epita.filrouge.application.person.AppUserDto;
import fr.epita.filrouge.application.viewingserie.ViewingSerieCreateDto;
import fr.epita.filrouge.application.viewingserie.ViewingSerieService;
import fr.epita.filrouge.application.viewingserie.ViewingSerieServiceImpl;
import fr.epita.filrouge.domain.entity.common.Category;
import fr.epita.filrouge.domain.entity.common.PublicNotation;
import fr.epita.filrouge.domain.entity.common.Status;
import fr.epita.filrouge.domain.entity.person.AppUser;
import fr.epita.filrouge.domain.entity.person.Role;
import fr.epita.filrouge.domain.entity.serie.Serie;
import fr.epita.filrouge.domain.entity.viewingserie.ViewingSerie;
import fr.epita.filrouge.domain.entity.viewingserie.ViewingSerieRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDate;

import static fr.epita.filrouge.domain.entity.common.Status.TO_WATCH;

@ComponentScan(basePackages = "fr.epita.filrouge")
@SpringBootTest(classes = {ViewingSerieServiceImpl.class})
public class ViewingSerieTest {


    @Autowired
    private ViewingSerieService viewingSerieService;

    @MockBean

    private ViewingSerieRepository viewingSerieRepository;

    @MockBean

    private MapperViewingSerieDto mapperViewingSerieDto;

    @MockBean

    private AppUserDtoMapper appUserDtoMapper;



//
//    @Test
//    @DisplayName("tester la bonne création d'une série")
//    public void createSerie_should_success() {
//
//
//        //Given
//        //Given
//        SerieDto serieDto = new SerieDto ();
//        serieDto.setCategory (Category.COMEDY);
//        serieDto.setImdbId ("tt00021");
//        serieDto.setDescription ("Prison Break");
//        serieDto.setNumberOfEpisode (20);
//        serieDto.setNumberOfSeason (5);
//        serieDto.setStartYear (1963);
//        serieDto.setEndYear (1969);
//
//        Serie serieMock = new Serie ();
//        serieMock.setCategory (Category.COMEDY);
//        serieMock.setImdbId ("tt00021");
//        serieMock.setDescription ("Prison Break");
//        serieMock.setNumberOfEpisode (20);
//        serieMock.setNumberOfSeason (5);
//        serieMock.setStartYear (1963);
//        serieMock.setEndYear (1969);
//
//        AppUser appUser = AppUser.Builder.anAppUser()
//                .withFirstname("Jean")
//                .withLastname("Durant")
//                .withEmail("yoss@test.com")
//                .withBirthdayDate(LocalDate.of(1950, 12, 25))
//                .withRole(Role.ROLE_RESP)
//                .withPassword("testpass")
//                .build();
//
//        ViewingSerieCreateDto  viewingSerieDto = new ViewingSerieCreateDto ();
//        viewingSerieDto.setCurrentEpisode (1);
//        viewingSerieDto.setCurrentSeason (1);
//        viewingSerieDto.setEmail ("yoss@test.com");
//        viewingSerieDto.setStatus (TO_WATCH);
//
//
//
//    }

}