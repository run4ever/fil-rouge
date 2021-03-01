package fr.epita.filrouge.application.person;

import fr.epita.filrouge.application.mapper.AppUserDtoMapper;
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

    @Autowired
    private AppUserDtoMapper appUserDtoMapper;

    @Override
    public void create(AppUserDto appUserDto) {
            //RG : si appUser existe déjà alors pas possible de le recréer.
            // le contrôle est basé sur l'adresse email.
        final AppUser appUserFound = appUserRepository.findbyEmail(appUserDto.getEmail());
        if(appUserFound != null) {
            //appUser existe déjà avec le même adresse email
            throw new AlreadyExistingException("AppUser already existing "+appUserDto.getEmail(), ErrorCodes.USER_ALREADY_EXISTING);
        }

        appUserRepository.create(appUserDtoMapper.mapDtoToDomain(appUserDto));
    }

    @Override
    public AppUserLightDto getAppUser(String email) {
        if(email == null) {
            return null;
        }

        return appUserDtoMapper.mapDomainToLightDto(appUserRepository.findbyEmail(email));
    }
}
