package fr.epita.filrouge.infrastructure.person;

import fr.epita.filrouge.domain.entity.person.AppUser;
import fr.epita.filrouge.domain.entity.person.AppUserRepository;
import fr.epita.filrouge.infrastructure.mapper.AppUserJpaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AppUserRepositoryImpl implements AppUserRepository {

    private static Logger logger = LoggerFactory.getLogger(AppUserRepositoryImpl.class);

    @Autowired
    private AppUserJpaRepository appUserJpaRepository;

    @Autowired
    private AppUserJpaMapper appUserJpaMapper;

    @Override
    public AppUser findbyEmail(String email) {
        logger.info("AppUser JPA impl, findByEmail : " + email);
        //TODO : Ici il faut gérer le cas AppUserJpa non trouvé par email NotFoundException
        return appUserJpaMapper.mapToEntity(appUserJpaRepository.findByEmail(email));
    }

    @Override
    public void create(AppUser appUser) {
        logger.info("AppUser JPA impl, create : " + appUser.getEmail());
        appUserJpaRepository.save(appUserJpaMapper.mapToJpa(appUser));
    }
}
