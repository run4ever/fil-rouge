package fr.epita.filrouge.application.mapper;

import fr.epita.filrouge.domain.entity.serie.Serie;
import fr.epita.filrouge.application.serie.SerieDto;

import java.util.List;

public interface SerieDtoMapper {

        Serie mapDtoToDomain(SerieDto serieDto);
        SerieDto mapDomainToDto (Serie serie);
        List<SerieDto> mapDomainToDto (List<Serie> serieList);
 }


