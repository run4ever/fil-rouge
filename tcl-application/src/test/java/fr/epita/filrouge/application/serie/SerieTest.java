package fr.epita.filrouge.application.serie;

import fr.epita.filrouge.domain.entity.common.Category;
import fr.epita.filrouge.domain.entity.serie.Serie;
import fr.epita.filrouge.domain.entity.serie.SerieRepository;
import fr.epita.filrouge.domain.exception.AlreadyExistingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ComponentScan(basePackages = "fr.epita.filrouge")
@SpringBootTest(classes = {SerieServiceImpl.class})
public class SerieTest {

    @Autowired
    private SerieService serieService;

    @MockBean
    private SerieRepository serieRepositoryMock;


    @Test
    @DisplayName("tester la bonne création d'une série")
    public void createSerie_should_success() {

        Serie serie20 = Serie.Builder.aSerie ()
                .withImdbId ("tt00020")
                .withTitle ("serie tt00020")
                .withDescription ("la série dans le service20")
                .withStartYear (1963)
                .withEndYear (1969)
                .withNumberOfSeason (5)
                .withNumberOfEpisode (20)
                .withCategory (Category.THRILLER)
                .build ();

        /** Mock sur Repository */
        when(serieRepositoryMock.findById ("tt00020")).thenReturn (null);

        serieService.createSerie (serie20);

        //then

        verify(serieRepositoryMock,times(1)).create(serie20);


    }

    @Test
    public void create_existing_serie_should_crash(){
        //Given
        Serie serie21 = Serie.Builder.aSerie ()
                .withImdbId ("tt00021")
                .withTitle ("serie tt00021")
                .withDescription ("la série dans le service21")
                .withStartYear (1973)
                .withEndYear (1979)
                .withNumberOfSeason (5)
                .withNumberOfEpisode (20)
                .withCategory (Category.THRILLER)
                .build ();

        when(serieRepositoryMock.findById ("tt00021")).thenReturn(serie21);

        //When
        try{
            serieService.createSerie (serie21);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(AlreadyExistingException.class);
        }

        //Then
        verify(serieRepositoryMock,never()).create(serie21);
    }

    @Test
    public void find_serie_should_success(){
        //Given
        Serie serie23 = Serie.Builder.aSerie ()
                .withImdbId ("tt00023")
                .withTitle ("serie tt00023")
                .withDescription ("la série dans le service23")
                .withStartYear (1983)
                .withEndYear (1989)
                .withNumberOfSeason (5)
                .withNumberOfEpisode (20)
                .withCategory (Category.COMEDY)
                .build ();


        when(serieRepositoryMock.findById ("tt00023")).thenReturn (serie23);

        //When
        final Serie result = serieService.getSerieById ("tt00023");

        //Then
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("serie tt00023");



    }



}
