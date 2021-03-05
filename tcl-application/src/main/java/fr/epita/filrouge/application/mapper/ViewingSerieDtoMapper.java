package fr.epita.filrouge.application.mapper;

import fr.epita.filrouge.application.viewingserie.ViewingSerieCreateDto;
import fr.epita.filrouge.application.viewingserie.ViewingSerieRestitDto;
import fr.epita.filrouge.domain.entity.viewingserie.ViewingSerie;

public interface ViewingSerieDtoMapper {

    ViewingSerieCreateDto mapToDtoCreate(ViewingSerie viewingSerie);
    ViewingSerie mapToDomainCreate(ViewingSerieCreateDto viewingSerieCreateDto);
    ViewingSerieRestitDto mapToDtoRestit(ViewingSerie viewingSerie);
}
