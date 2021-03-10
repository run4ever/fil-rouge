package fr.epita.filrouge.domain.entity.person;

public interface AppUserRepository {
    AppUser findbyEmail(String email);
    AppUser create(AppUser appUser);
    Boolean authentificatedAppUser(String email, String password);
}
