package fr.epita.filrouge.application.serie;

import fr.epita.filrouge.application.mapper.MapperSerieDto;
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

    @MockBean
    private MapperSerieDto mapperSerieDto;


    @Test
    @DisplayName("tester la bonne création d'une série")
    public void createSerie_should_success() {

        //Given
        SerieDto serieDto = new SerieDto ();
        serieDto.setCategory (Category.COMEDY);
        serieDto.setImdbId ("tt00021");
        serieDto.setDescription ("Prison Break");
        serieDto.setNumberOfEpisode (20);
        serieDto.setNumberOfSeason (5);
        serieDto.setStartYear (1963);
        serieDto.setEndYear (1969);

        Serie serieMock = new Serie ();
        serieMock.setCategory (Category.COMEDY);
        serieMock.setImdbId ("tt00021");
        serieMock.setDescription ("Prison Break");
        serieMock.setNumberOfEpisode (20);
        serieMock.setNumberOfSeason (5);
        serieMock.setStartYear (1963);
        serieMock.setEndYear (1969);


        /** Mock sur Mapper */
        when (mapperSerieDto.mapDtoToDomain (serieDto)).thenReturn (serieMock);
        when (mapperSerieDto.mapDomainToDto (serieMock)).thenReturn (serieDto);

        /** Mock sur Repository */
        when (serieRepositoryMock.findById (serieDto.getImdbId ())).thenReturn (null);
        when (serieRepositoryMock.create (serieMock)).thenReturn (serieMock);


        //when
        /** Création de la série Dto */
        SerieDto serieResult = serieService.createSerie (serieDto);


        assertThat(serieResult.getCategory ()).isEqualTo (Category.COMEDY);
        assertThat(serieResult.getDescription ()).isEqualTo ("Prison Break");

    }



}
