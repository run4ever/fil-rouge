package fr.epita.filrouge.application.person;

import fr.epita.filrouge.application.mapper.AppUserDtoMapper;
import fr.epita.filrouge.domain.entity.person.AppUser;
import fr.epita.filrouge.domain.entity.person.AppUserRepository;
import fr.epita.filrouge.domain.exception.AlreadyExistingException;
import fr.epita.filrouge.domain.exception.ErrorCodes;
import fr.epita.filrouge.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppUserDtoMapper appUserDtoMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUserDto create(AppUserDto appUserDto) {
            //RG : si appUser existe déjà alors pas possible de le recréer.
            // le contrôle est basé sur l'adresse email.
        AppUserDto user = new AppUserDto();
        try {
            final AppUser appUserFound = appUserRepository.findbyEmail(appUserDto.getEmail());
            if(appUserFound != null) {
                //appUser existe déjà avec le même adresse email
                throw new AlreadyExistingException("AppUser already existing "+appUserDto.getEmail(), ErrorCodes.USER_ALREADY_EXISTING);
            }
        } catch (NotFoundException e) {
            //si on n'a pas trouvé AppUser avec email en question alors on peut créer Appuser
            //crypter le mot de passe avant
            appUserDto.setPassword(passwordEncoder.encode(appUserDto.getPassword()));
            System.out.println("Password crypted=>"+appUserDto.getPassword());
            user = appUserDtoMapper.mapDomainToDto(appUserRepository.create(appUserDtoMapper.mapDtoToDomain(appUserDto)));
        }
        return user;
    }

    @Override
    public AppUserLightDto getAppUser(String email) {
        if(email == null) {
            return null;
        }

        return appUserDtoMapper.mapDomainToLightDto(appUserRepository.findbyEmail(email));
    }
}
