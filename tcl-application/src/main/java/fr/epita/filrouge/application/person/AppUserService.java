package fr.epita.filrouge.application.person;

import fr.epita.filrouge.domain.entity.person.AppUser;

public interface AppUserService {
    void create(AppUser appUser);
    AppUser getAppUser(String email);
}
