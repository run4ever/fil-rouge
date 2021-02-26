package fr.epita.filrouge.application.person;



public interface AppUserService {
    void create(AppUserDto appUserDto);
    AppUserDto getAppUser(String email);
}
