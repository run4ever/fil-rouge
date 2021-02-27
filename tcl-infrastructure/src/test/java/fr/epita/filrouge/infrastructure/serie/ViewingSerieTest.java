package fr.epita.filrouge.infrastructure.serie;

import fr.epita.filrouge.domain.entity.common.Category;
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

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class ViewingSerieTest {

    @Autowired
    private ViewingSerieRepository viewingSerieDomaine;


    Serie serie15 = Serie.Builder.aSerie ()
            .withImdbId ("tt00015")
            .withTitle ("serie tt00015")
            .withCategory (Category.THRILLER)
            .build ();

    Serie serie11 = Serie.Builder.aSerie ()
            .withImdbId ("tt00011")
            .withTitle ("serie tt00011")
            .withCategory (Category.ROMANCE)
            .build ();


    Serie serie14 = Serie.Builder.aSerie ()
            .withImdbId ("tt00014")
            .withTitle ("serie tt00014")
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


    ViewingSerie viewingSerie11 = ViewingSerie.Builder.aViewingSerie ()
            .withAppUser (appUser)
            .withSerie (serie11)
            .withCurrentEpisode (2)
            .withCurrentSeason (5)
            .withStatus (Status.TO_WATCH)
            .build ();

    ViewingSerie viewingSerie14 = ViewingSerie.Builder.aViewingSerie ()
            .withAppUser (appUser)
            .withSerie (serie14)
            .withCurrentEpisode (60)
            .withCurrentSeason (10)
            .withStatus (Status.IN_PROGESS)
            .build ();

    ViewingSerie viewingSerie15 = ViewingSerie.Builder.aViewingSerie ()
            .withAppUser (appUser)
            .withSerie (serie15)
            .withCurrentEpisode (60)
            .withCurrentSeason (10)
            .withStatus (Status.IN_PROGESS)
            .build ();

    @Test
    @DisplayName("Vérifier la création de la viewing Serie")
    public void VerifyCreationOfVievingSerie() {

        try {
            System.out.println (viewingSerie11.toString ());
            viewingSerieDomaine.create(viewingSerie11);

        }
        catch (Exception e) {
            e.printStackTrace ();
        }

       assertThat (viewingSerie11.getAppUser ().getEmail ()).isEqualTo("brad@pitt.com");
    }


    @Test

    @DisplayName("Vérifier la récupération des visionnages")
    public void VerifyGetAllViewingSerie (){

        int sizeListViewing = viewingSerieDomaine.findAllVievingSerie ().size ();
        viewingSerieDomaine.create(viewingSerie14);
        viewingSerieDomaine.create(viewingSerie15);
        sizeListViewing +=2;

        assertThat (viewingSerieDomaine.findAllVievingSerie ().size ()).isEqualTo (sizeListViewing);

    }

}

