package fr.epita.filrouge.application.mapper;

import fr.epita.filrouge.application.viewingserie.ViewingSerieRestitDto;
import fr.epita.filrouge.domain.entity.viewingserie.ViewingSerie;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = MapperSerieDto.class)
public interface MapperViewingSerieDto {

    ViewingSerie mapDtoToDomain (ViewingSerieRestitDto serieViewDto);

    ViewingSerieRestitDto mapDomainToDto (ViewingSerie serieView);
}
