package fr.epita.filrouge.infrastructure.movie;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)   //Junit 4
//@ExtendWith(SpringExtension.class) //Junit 5
@DataJpaTest

public class ExamplesTests {

    @Test
    public void exmple_test()  {
        //Given
            //*** les variables d'entrée

        //When
            //*** appel de la méthode à tester ici

        //Then
            //*** AssertThat ici pour vérifier les résultats

        System.out.println("Example test!!!");
    }
}
