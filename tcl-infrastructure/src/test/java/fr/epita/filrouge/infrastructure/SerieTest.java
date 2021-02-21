package fr.epita.filrouge.infrastructure;

import fr.epita.filrouge.domain.entity.common.Category;
import fr.epita.filrouge.domain.entity.serie.Serie;
import fr.epita.filrouge.domain.entity.serie.ISerie;
import fr.epita.filrouge.infrastructure.serie.ISerieRepository;
import fr.epita.filrouge.infrastructure.mapper.SerieJpaMapper;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ComponentScan(basePackages = { "fr.epita.filrouge" }, lazyInit = true)
@EntityScan(basePackages = { "fr.epita.filrouge.infrastructure.*"})
@ExtendWith(SpringExtension.class) //Junit 5
//@DataJpaTest
@SpringBootTest
public class SerieTest {


    @Autowired
    private ISerie serieDomaine;

    @Autowired
    private SerieJpaMapper serieJpaMapper;

    @Autowired
    private ISerieRepository iSerieRepository;

    @Test
    @DisplayName("Vérifier la création et récupération d'une série")
    public void VerifyCreationOfSerie() {

        Serie serie1 = new Serie ("tt0001",
                "UJA",
                "serie1",
                2018,
                2019,
                5,
                20,
                Category.ROMANCE
        );

        Serie serie2 = new Serie ("tt0002",
                "UJA",
                "serie1",
                2018,
                2019,
                5,
                20,
                Category.ROMANCE
        );

        serieDomaine.create (serie1);
        serieDomaine.create (serie2);

        Assert.assertEquals (serie1.getCategory (), serie2.getCategory ());
        //Then
        //*** AssertThat ici pour vérifier les résultats


        }

    @Test
    @DisplayName("Vérifier la récupération avec id")
    public void VerifyRecuperartionbyId() {




    }


        }
