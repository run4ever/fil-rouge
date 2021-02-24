package fr.epita.filrouge.infrastructure.serie;

import fr.epita.filrouge.domain.entity.common.Category;
import fr.epita.filrouge.domain.entity.common.Status;
import fr.epita.filrouge.domain.entity.person.AppUser;
import fr.epita.filrouge.domain.entity.person.AppUserRepository;
import fr.epita.filrouge.domain.entity.person.Role;
import fr.epita.filrouge.domain.entity.serie.Serie;
import fr.epita.filrouge.domain.entity.serie.ViewingSerie;
import fr.epita.filrouge.domain.entity.serie.ViewingSerieRepository;
import fr.epita.filrouge.infrastructure.mapper.ViewingSerieJpaMapper;
import fr.epita.filrouge.infrastructure.person.AppUserJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class ViewingSerieTest {

    @Autowired
    private ViewingSerieJpaRepository viewingSerieJpaRepository;

    @Autowired
    private ViewingSerieJpaMapper viewingSerieJpaMapper;

    @Autowired
    private ViewingSerieRepository viewingSerieDomaine;

    @Autowired
    private AppUserJpaRepository appUserJpaRepository;

    @Autowired
    private AppUserRepository appUserRepository;


    Serie serie10 = Serie.Builder.aSerie ()
            .withImdbId ("tt00010")
            .withTitle ("serie tt00010")
            .withCategory (Category.THRILLER)
            .build ();

    Serie serie11 = Serie.Builder.aSerie ()
            .withImdbId ("tt00011")
            .withTitle ("serie tt00011")
            .withCategory (Category.ROMANCE)
            .build ();

    AppUser appUser = AppUser.Builder.anAppUser()
            .withFirstname("Brad")
            .withLastname("Pitt")
            .withEmail("brad@pitt.com")
            .withBirthdayDate(LocalDate.of(1963, 12, 18))
            .withRole(Role.ROLE_USER)
            .withPassword("superman")
            .build();

    AppUser appBadUser = AppUser.Builder.anAppUser()
            .withFirstname("Brave")
            .withLastname("Type")
            .withEmail("Brave.Type@gmail.com")
            .withBirthdayDate(LocalDate.of(1963, 12, 18))
            .withRole(Role.ROLE_USER)
            .withPassword("superman")
            .build();

    ViewingSerie viewingSerie10 = ViewingSerie.Builder.aViewingSerie ()
            .withAppUser (appUser)
            .withSerie (serie10)
            .withCurrentEpisode (2)
            .withCurrentSeason (5)
            .withStatus (Status.TO_WATCH)
            .build ();

    ViewingSerie viewingSerie11 = ViewingSerie.Builder.aViewingSerie ()
            .withAppUser (appUser)
            .withSerie (serie11)
            .withCurrentEpisode (60)
            .withCurrentSeason (10)
            .withStatus (Status.IN_PROGESS)
            .build ();

    @Test
    @DisplayName("Vérifier la création de la viewing Serie")
    public void VerifyCreationOfVievingSerie() {

        try {
            System.out.println (viewingSerie10.toString ());
            viewingSerieDomaine.create(viewingSerie10);

        }
        catch (Exception e) {
            e.printStackTrace ();
        }

       assertThat (viewingSerie10.getAppUser ().getEmail ()).isEqualTo("brad@pitt.com");
    }


    @Test

    @DisplayName("Vérifier la récupération des visionnages")
    public void VerifyGetAllViewingSerie (){

        int sizeListViewing = viewingSerieDomaine.findAllVievingSerie ().size ();
        viewingSerieDomaine.create(viewingSerie10);
        viewingSerieDomaine.create(viewingSerie11);
        sizeListViewing +=2;

        assertThat (viewingSerieDomaine.findAllVievingSerie ().size ()).isEqualTo (sizeListViewing);

    }

}

