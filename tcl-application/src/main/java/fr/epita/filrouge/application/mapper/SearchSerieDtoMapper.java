package fr.epita.filrouge.application.mapper;


import fr.epita.filrouge.application.serie.SearchSerieDto;
import fr.epita.filrouge.domain.entity.serie.SearchSerie;
import fr.epita.filrouge.domain.entity.serie.Serie;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface SearchSerieDtoMapper {

    SearchSerie mapDtoToDomain(SearchSerieDto searchSerieDto);
    SearchSerieDto mapDomainToDto (SearchSerie serie);
    List<SearchSerie> mapDtoToDomainList(List<SearchSerieDto> searchSerieDtos);
    List<SearchSerieDto> mapDomainToDtoList (List<SearchSerie> series);
}
