package fr.epita.filrouge.application.person;

import fr.epita.filrouge.domain.entity.person.AppUser;
import fr.epita.filrouge.domain.entity.person.AppUserRepository;
import fr.epita.filrouge.domain.entity.person.Role;
import fr.epita.filrouge.domain.exception.AlreadyExistingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.time.LocalDate;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {AppUserServiceImpl.class})
public class AppUserTest {

    @Autowired
    private AppUserService appUserService;

    //AppUserRepository fait l'accès à la base de données (couche Infrastructure).
    //L'accès à la base de données est testé dans la couche infra, il faut mocker ici.
    @MockBean
    private AppUserRepository appUserRepositoryMock;

    @Test
    @DisplayName("AppUser création est succes si appUser n'exist pas encore")
    public void createAppUser_should_success_when_not_aleady_exist() {
        //Given
        AppUser appUser = AppUser.Builder.anAppUser()
                .withFirstname("Jean")
                .withLastname("Durant")
                .withEmail("jean@durant.fr")
                .withBirthdayDate(LocalDate.of(1950, 12, 25))
                .withRole(Role.ROLE_RESP)
                .withPassword("testpass")
                .build();

        /** Mock sur AppUserRepository */
        when(appUserRepositoryMock.findbyEmail("jean@durant.fr")).thenReturn(null);

        //When
        appUserService.create(appUser);

        //Then
        /** vérifier si la méthode create de appUserRepositoryMock est appelé 1 fois */
        verify(appUserRepositoryMock, times(1)).create(appUser);
    }

    @Test
    @DisplayName("Appuser création est KO si appUser email existe déjà")
    public void createAppUser_should_fail_when_already_exist() {
        //Given
        AppUser appUser = AppUser.Builder.anAppUser()
                .withFirstname("Jean")
                .withLastname("Durant")
                .withEmail("jean@durant.fr")
                .withBirthdayDate(LocalDate.of(1950, 12, 25))
                .withRole(Role.ROLE_RESP)
                .withPassword("testpass")
                .build();

        /** Mock on retourne systématiquement un AppUser quand on appelle findByEmail */
        when(appUserRepositoryMock.findbyEmail("jean@durant.fr")).thenReturn(appUser);

        //When
        try {
            appUserService.create(appUser);
        } catch (final Exception e) {
            //si Email existe déjà, on a levé une AlreadyExistingException dans appUserService.create
            //Ici on doit avoir sytématiquement cette exception, car on a Mock pour findbyEmail
            assertThat(e).isInstanceOf(AlreadyExistingException.class);
        }

        //Then
        /** vérifier que la méthode create de appUserRepositoryMock n'est jamais appelée */
        verify(appUserRepositoryMock,never()).create(appUser);
    }

}
