package fr.epita.filrouge.application.mapper;

import fr.epita.filrouge.application.person.AppUserDto;
import fr.epita.filrouge.domain.entity.person.AppUser;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel="spring")
public interface AppUserDtoMapper {



    AppUser mapDtoToDomain(AppUserDto appUserDto);
    AppUserDto mapDomainToDto(AppUser appUser);

}
