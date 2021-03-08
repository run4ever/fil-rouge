package fr.epita.filrouge.application.mapper;

import fr.epita.filrouge.application.viewingserie.ViewingSerieCreateDto;
import fr.epita.filrouge.application.viewingserie.ViewingSerieRestitDto;
import fr.epita.filrouge.domain.entity.person.AppUserRepository;
import fr.epita.filrouge.domain.entity.serie.SerieRepository;
import fr.epita.filrouge.domain.entity.viewingserie.ViewingSerie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ViewingSerieDtoMapperImpl implements ViewingSerieDtoMapper{

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private SerieRepository serieRepository;

    @Autowired
    private SerieDtoMapper serieDtoMapper;


    public ViewingSerieCreateDto mapToDtoCreate(ViewingSerie viewingSerie) {
        ViewingSerieCreateDto viewingSerieCreateDto = new ViewingSerieCreateDto ();
        viewingSerieCreateDto.setStatus (viewingSerie.getStatus ());
        viewingSerieCreateDto.setImdbId (viewingSerie.getSerie ().getImdbId ());
        viewingSerieCreateDto.setEmail (viewingSerie.getAppUser ().getEmail ());
        viewingSerieCreateDto.setCurrentSeason (viewingSerie.getCurrentSeason ());
        viewingSerieCreateDto.setCurrentEpisode (viewingSerie.getCurrentEpisode ());
        return viewingSerieCreateDto;
    }

    public ViewingSerie mapToDomainCreate(ViewingSerieCreateDto viewingSerieCreateDto) {
        ViewingSerie viewingSerie = new ViewingSerie ();
        viewingSerie.setStatus (viewingSerieCreateDto.getStatus ());
        viewingSerie.setSerie (serieRepository.findById (viewingSerieCreateDto.getImdbId ()));
        viewingSerie.setCurrentSeason (viewingSerieCreateDto.getCurrentSeason ());
        viewingSerie.setCurrentEpisode (viewingSerieCreateDto.getCurrentEpisode ());
        viewingSerie.setAppUser (appUserRepository.findbyEmail (viewingSerieCreateDto.getEmail ()));
        return viewingSerie;
    }

    public ViewingSerieRestitDto mapToDtoRestit(ViewingSerie viewingSerie) {
        ViewingSerieRestitDto viewingSerieRestitDto = new ViewingSerieRestitDto ();
        viewingSerieRestitDto.setCurrentEpisode (viewingSerie.getCurrentEpisode ());
        viewingSerieRestitDto.setCurrentSeason (viewingSerie.getCurrentSeason ());
        viewingSerieRestitDto.setEmail (viewingSerie.getAppUser ().getEmail ());
        viewingSerieRestitDto.setSerieDto (serieDtoMapper.mapDomainToDto (viewingSerie.getSerie ()));
        viewingSerieRestitDto.setStatus (viewingSerie.getStatus ());
        return viewingSerieRestitDto;
    }
}
