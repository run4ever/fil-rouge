package fr.epita.filrouge.domain.entity.person;

public interface AppUserRepository {
    AppUser findbyEmail(String email);
    void create(AppUser appUser);
}
