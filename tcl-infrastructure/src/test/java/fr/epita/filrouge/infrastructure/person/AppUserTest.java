package fr.epita.filrouge.infrastructure.person;

import fr.epita.filrouge.domain.entity.person.AppUser;
import fr.epita.filrouge.domain.entity.person.AppUserRepository;
import fr.epita.filrouge.domain.entity.person.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
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

    @Test
    @DisplayName("Authentification d'un AppUser")
    public void given_a_valid_AppUser_auhtentification_should_be_success() {
        //Given
        AppUser appUser = AppUser.Builder.anAppUser()
                .withFirstname("Brad")
                .withLastname("Pitt")
                .withEmail("brad@pitt.com")
                .withBirthdayDate(LocalDate.of(1963, 12, 18))
                .withRole(Role.ROLE_USER)
                .withPassword("superman")
                .build();

        String email = "brad@pitt.com";
        String password = "superman";

        //When
        //*** sauvegarder d'abord AppUser, puis appeler la méthode authentification
        appUserRepository.create(appUser);
        Boolean isAuthentificated = appUserRepository.authentificatedAppUser(email, password);

        //Then
        assertThat(isAuthentificated).isEqualTo(true);
    }
}
