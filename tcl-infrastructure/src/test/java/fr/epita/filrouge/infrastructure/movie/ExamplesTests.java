package fr.epita.filrouge.infrastructure.movie;


import fr.epita.filrouge.domain.entity.person.AppUser;
import fr.epita.filrouge.domain.entity.person.AppUserRepository;
import fr.epita.filrouge.domain.entity.person.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

//@RunWith(SpringRunner.class)   //Junit 4
@ExtendWith(SpringExtension.class) //Junit 5
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
