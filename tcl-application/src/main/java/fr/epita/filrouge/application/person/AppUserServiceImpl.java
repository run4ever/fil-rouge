package fr.epita.filrouge.application.person;

import fr.epita.filrouge.domain.entity.person.AppUser;
import fr.epita.filrouge.domain.entity.person.AppUserRepository;
import fr.epita.filrouge.domain.exception.AlreadyExistingException;
import fr.epita.filrouge.domain.exception.ErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public void create(AppUser appUser) {
            //RG : si appUser existe déjà alors pas possible de le recréer.
            // le contrôle est basé sur l'adresse email.
        final AppUser appUserFound = appUserRepository.findbyEmail(appUser.getEmail());
        if(appUserFound != null) {
            //appUser existe déjà avec le même adresse email
            throw new AlreadyExistingException("AppUser already existing "+appUser.getEmail(), ErrorCodes.USER_ALREADY_EXISTING);
        }

        appUserRepository.create(appUser);
    }

    @Override
    public AppUser getAppUser(String email) {
        if(email != null) {
            appUserRepository.findbyEmail(email);
        }
        return null;
    }
}
