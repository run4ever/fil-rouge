package fr.epita.filrouge.infrastructure.person;

import fr.epita.filrouge.domain.entity.person.AppUser;
import fr.epita.filrouge.domain.entity.person.AppUserRepository;
import fr.epita.filrouge.domain.entity.person.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

//@ExtendWith(SpringExtension.class) //Junit 5
@DataJpaTest

public class AppUserTest {

    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    @DisplayName("Vérifier la création et récupération d'un AppUser")
    public void given_appUser_should_save_in_database() {
        //Given
        AppUser appUser = AppUser.Builder.anAppUser()
                                .withFirstname("Jean")
                                .withLastname("Dupont")
                                .withEmail("jean.dupond@gmail.com")
                                .withBirthdayDate(LocalDate.of(1970, 12, 25))
                                .withRole(Role.ROLE_RESP)
                                .withPassword("superman")
                                .build();

        //When
        appUserRepository.create(appUser);

        //Then
        AppUser appUserFound = appUserRepository.findbyEmail("jean.dupond@gmail.com");
        assertThat(appUserFound).isNotNull();
        assertThat(appUserFound.getEmail()).isEqualTo("jean.dupond@gmail.com");
        assertThat(appUserFound.getLastname()).isEqualTo("Dupont");
        assertThat(appUserFound.getRole()).isEqualTo(Role.ROLE_RESP);

    }

    @Test
    @DisplayName("AppUser non trouvé avec email inexistant")
    public void email_not_exists_should_return_error() {
        //Given
            String email = "ko_ko_ko";

        //When
        AppUser appUser = appUserRepository.findbyEmail(email);

        //Then
        //**** Il faut avoir un retour AppUserNotFoundException c'est mieux
        assertThat(appUser).isNull();

    }
}
