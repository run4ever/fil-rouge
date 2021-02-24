package fr.epita.filrouge.infrastructure.serie;

import fr.epita.filrouge.domain.entity.common.Category;
import fr.epita.filrouge.domain.entity.serie.Serie;
import fr.epita.filrouge.domain.entity.serie.SerieRepository;
import fr.epita.filrouge.infrastructure.mapper.SerieJpaMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class SerieTest {


    @Autowired
    private SerieRepository serieDomaine;

    @Autowired
    private SerieJpaMapper serieJpaMapper;

    @Autowired
    private SerieJpaRepository iSerieRepository;


   Serie serieb = Serie.Builder.aSerie ()
           .withImdbId ("tt00010")
           .withTitle ("serie builer")
           .withCategory (Category.THRILLER)
           .build ();




    private static final Serie serie1 = new Serie ("tt0001",
            "UJA",
            "serie1",
            2018,
            2019,
            5,
            20,
            Category.ROMANCE
    );

    private static final Serie serie_to_delete = new Serie ("tt_test_suppr3",
            "UJA",
            "serie1",
            2018,
            2019,
            5,
            20,
            Category.ROMANCE
    );

    private static final Serie serie5 = new Serie ("tt0005",
            "UJA",
            "serie5",
            2018,
            2019,
            5,
            20,
            Category.ROMANCE
    );
    private static final Serie serie9 = new Serie ("tt0001", "UJA", "serie9", 2018, 2019, 5, 20, Category.ROMANCE);

    private static final Serie serie10 = new Serie ("tt0002",
            "UJA",
            "serie10",
            2018,
            2019,
            5,
            20,
            Category.ROMANCE
    );

    private static final Serie serie11 = new Serie ("tt0005",
            "UJA",
            "serie11",
            2018,
            2019,
            5,
            20,
            Category.ROMANCE
    );
    @Test
    @DisplayName("Vérifier la création et récupération d'une série")
    public void VerifyCreationOfSerie() {



        assertThat(serieDomaine.findById("tt00010").getTitle()).isEqualTo("serie builer");

        }

    @Test
    @DisplayName("Vérifier la récupération de la liste des series")
    public void VerifyGetAllSeries() {

        int count = serieDomaine.findAllSeries().size ();


        serieDomaine.create (serie9);
        serieDomaine.create (serie10);
        serieDomaine.create (serie11);
        assertThat(serieDomaine.findAllSeries ().size()).isEqualTo(3+count);

    }
    @Test
    @Transactional
    @DisplayName("Vérifier la suppression d'une série")
    public void VerifyDeleteSerie() {

        serieDomaine.create (serie_to_delete);
        serieDomaine.deleteSerie ("tt_test_suppr3");
        assertThat(serieDomaine.findById ("tt_test_suppr3")).isNull ();
    }

}
