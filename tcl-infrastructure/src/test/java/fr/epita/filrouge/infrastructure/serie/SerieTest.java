package fr.epita.filrouge.infrastructure.serie;

import fr.epita.filrouge.domain.entity.common.Category;
import fr.epita.filrouge.domain.entity.common.PublicNotation;
import fr.epita.filrouge.domain.entity.serie.Serie;
import fr.epita.filrouge.domain.entity.serie.SerieRepository;
import fr.epita.filrouge.domain.entity.serie.StatusSerie;
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


    private static final Serie serie_to_delete = Serie.Builder.aSerie()
            .withImdbId("tt_test_suppr3")
            .withDescription("UJA")
            .withTitle("Serie1")
            .withStartYear(2018)
            .withEndYear(2019)
            .withImageUrl("https://m.media-amazon.com/images/M/MV5BNzQzMzJhZTEtOWM4NS00MTdhLTg0YjgtMjM4MDRkZjUwZDBlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg")
            .withStatusSerie(StatusSerie.FINISH)
            .withNumberOfSeason(8)
            .withNumberOfEpisode(100)
            .withActors("Les supers actors du monde")
            .withCategory(Category.ROMANCE)
            .withPublicNotation(new PublicNotation(5D,2530))
            .build();

       private static final Serie serie5 = Serie.Builder.aSerie()
            .withImdbId("tt0005")
            .withDescription("UJA")
            .withTitle("Serie5")
            .withStartYear(2018)
            .withEndYear(2019)
            .withImageUrl("https://m.media-amazon.com/images/M/MV5BNzQzMzJhZTEtOWM4NS00MTdhLTg0YjgtMjM4MDRkZjUwZDBlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg")
            .withStatusSerie(StatusSerie.FINISH)
            .withNumberOfSeason(8)
            .withNumberOfEpisode(100)
            .withActors("Les supers actors du monde")
            .withCategory(Category.ROMANCE)
            .withPublicNotation(new PublicNotation(5D,2530))
            .build();


    private static final Serie serie9 = Serie.Builder.aSerie()
            .withImdbId("tt0001")
            .withDescription("UJA")
            .withTitle("Serie5")
            .withStartYear(2018)
            .withEndYear(2019)
            .withImageUrl("https://m.media-amazon.com/images/M/MV5BNzQzMzJhZTEtOWM4NS00MTdhLTg0YjgtMjM4MDRkZjUwZDBlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg")
            .withStatusSerie(StatusSerie.FINISH)
            .withNumberOfSeason(8)
            .withNumberOfEpisode(100)
            .withActors("Les supers actors du monde")
            .withCategory(Category.ROMANCE)
            .withPublicNotation(new PublicNotation(5D,2530))
            .build();

    private static final Serie serie10 = Serie.Builder.aSerie()
            .withImdbId("tt0002")
            .withDescription("UJA")
            .withTitle("Serie5")
            .withStartYear(2018)
            .withEndYear(2019)
            .withImageUrl("https://m.media-amazon.com/images/M/MV5BNzQzMzJhZTEtOWM4NS00MTdhLTg0YjgtMjM4MDRkZjUwZDBlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg")
            .withStatusSerie(StatusSerie.FINISH)
            .withNumberOfSeason(8)
            .withNumberOfEpisode(100)
            .withActors("Les supers actors du monde")
            .withCategory(Category.ROMANCE)
            .withPublicNotation(new PublicNotation(5D,2530))
            .build();

    private static final Serie serie11 = Serie.Builder.aSerie()
            .withImdbId("tt0005")
            .withDescription("UJA")
            .withTitle("Serie5")
            .withStartYear(2018)
            .withEndYear(2019)
            .withImageUrl("https://m.media-amazon.com/images/M/MV5BNzQzMzJhZTEtOWM4NS00MTdhLTg0YjgtMjM4MDRkZjUwZDBlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg")
            .withStatusSerie(StatusSerie.FINISH)
            .withNumberOfSeason(8)
            .withNumberOfEpisode(100)
            .withActors("Les supers actors du monde")
            .withCategory(Category.ROMANCE)
            .withPublicNotation(new PublicNotation(5D,2530))
            .build();

    @Test
    @DisplayName("Vérifier la création et récupération d'une série")
    public void VerifyCreationOfSerie() {
        //Given
        Serie serie1 = Serie.Builder.aSerie()
                .withImdbId("tt0099")
                .withDescription("UJA")
                .withTitle("Serie5")
                .withStartYear(2018)
                .withEndYear(2019)
                .withImageUrl("https://m.media-amazon.com/images/M/MV5BNzQzMzJhZTEtOWM4NS00MTdhLTg0YjgtMjM4MDRkZjUwZDBlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg")
                .withStatusSerie(StatusSerie.FINISH)
                .withNumberOfSeason(8)
                .withNumberOfEpisode(100)
                .withActors("Les supers actors du monde")
                .withCategory(Category.ROMANCE)
                .withPublicNotation(new PublicNotation(5D,2530))
                .build();

        //When
        serieDomaine.create (serie1);

        //Then
        assertThat(serieDomaine.findById("tt0099").getTitle()).isEqualTo("Serie5");

    }

    @Test
    @DisplayName("Vérifier la récupération de la liste des series")
    public void VerifyGetAllSeries() {
        //Given
        int count = serieDomaine.findAllSeries().size ();

        //When
        serieDomaine.create (serie9);
        serieDomaine.create (serie10);
        serieDomaine.create (serie11);

        //Then
        assertThat(serieDomaine.findAllSeries ().size()).isEqualTo(3+count);

    }
    @Test
    @Transactional
    @DisplayName("Vérifier la suppression d'une série")
    public void VerifyDeleteSerie() {
        //Given
        serieDomaine.create (serie_to_delete);

        //When
        serieDomaine.deleteSerie ("tt_test_suppr3");

        //Then
        assertThat(serieDomaine.findById ("tt_test_suppr3")).isNull();
    }

}
