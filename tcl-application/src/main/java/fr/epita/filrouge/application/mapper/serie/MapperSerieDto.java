package fr.epita.filrouge.application.mapper.serie;

import fr.epita.filrouge.application.dto.serie.SerieDTO;
import fr.epita.filrouge.domain.entity.serie.Serie;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MapperSerieDto {

    Serie mapDtoToDomain(SerieDTO serieDTO);
    SerieDTO mapDomainToDto (Serie serie);
}
