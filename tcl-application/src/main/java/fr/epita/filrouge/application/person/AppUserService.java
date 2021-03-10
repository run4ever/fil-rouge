package fr.epita.filrouge.application.person;



public interface AppUserService {
    AppUserDto create(AppUserDto appUserDto);
    AppUserLightDto getAppUser(String email);
}
