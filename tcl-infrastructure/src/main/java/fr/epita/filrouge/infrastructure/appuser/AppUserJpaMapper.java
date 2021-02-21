package fr.epita.filrouge.infrastructure.appuser;

import fr.epita.filrouge.domain.entity.person.AppUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppUserJpaMapper {

    public AppUserJpa mapToJpa(AppUser entity){
        if (entity == null) {
            return null;
        }
        return AppUserJpa.Builder.anAppUserJpa()
                .withId(entity.getId())
                .withFirstname(entity.getFirstname())
                .withLastname(entity.getLastname())
                .withBirthdayDate(entity.getBirthdayDate())
                .withEmail(entity.getEmail())
                .withPassword(entity.getPassword())
                .withRole(entity.getRole())
                .build();
    }

    public List<AppUserJpa> mapToJpa(List<AppUser> entities){
        if (entities == null) {
            return null;
        }
        final List<AppUserJpa> appUsersJpa = new ArrayList<AppUserJpa> ();
        for( AppUser item : entities ) {
            appUsersJpa.add(mapToJpa(item));
        }
        return appUsersJpa;
    }

    public AppUser mapToEntity(AppUserJpa jpa){
        if (jpa == null) {
            return null;
        }

        final AppUser entity = AppUser.Builder.anAppUser()
                .withId(jpa.getId())
                .withFirstname(jpa.getFirstname())
                .withLastname(jpa.getLastname())
                .withBirthdayDate(jpa.getBirthdayDate())
                .withEmail(jpa.getEmail())
                .withPassword(jpa.getPassword())
                .withRole(jpa.getRole())
                .build();

        return entity;
    }

    public List<AppUser> mapToEntity(List<AppUserJpa> jpas){
        if (jpas == null) {
            return null;
        }
        final List<AppUser> appUsers = new ArrayList<AppUser>();
        for( AppUserJpa item : jpas ) {
            appUsers.add(mapToEntity(item));
        }
        return appUsers;
    }

}
