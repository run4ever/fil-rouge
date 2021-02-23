package fr.epita.filrouge.infrastructure.mapper;

import fr.epita.filrouge.domain.entity.serie.ViewingSerie;
import fr.epita.filrouge.infrastructure.serie.ViewingSerieJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ViewingSerieJpaMapper {

    @Autowired
    SerieJpaMapper serieJpaMapper;

    @Autowired
    AppUserJpaMapper appUserJpaMapper;

    public ViewingSerieJpa mapToJpa(ViewingSerie entity) {
        if (entity == null) {
            return null;
        }

        return ViewingSerieJpa.Builder.aViewingSerieJpa ()
                .withId (entity.getId ())
                .withCurrentSeason (entity.getCurrentSeason ())
                .withCurrentEpisode (entity.getCurrentEpisode ())
                .withStatus (entity.getStatus ())
                .withSerieJpa(serieJpaMapper.mapToJpa (entity.getSerie ()))
                .withAppUserjpa (appUserJpaMapper.mapToJpa (entity.getAppUser ()))
                .build ();
    }

    public List<ViewingSerieJpa> mapToJpa(List<ViewingSerie> entities) {
        if (entities == null) {
            return null;
        }
        final List<ViewingSerieJpa> viewingSeriesJpa = new ArrayList<ViewingSerieJpa> ();
        for (ViewingSerie item : entities) {
            viewingSeriesJpa.add (mapToJpa (item));
        }
        return viewingSeriesJpa;
    }

    public ViewingSerie mapToEntity(ViewingSerieJpa jpa){
        if (jpa == null) {
            return null;
        }

    final ViewingSerie entity = ViewingSerie.Builder.aViewingSerie ()
            .withId(jpa.getId())
            .withStatus(jpa.getStatus())
            .withCurrentSeason (jpa.getCurrentSeason ())
            .withCurrentEpisode (jpa.getCurrentEpisode ())
            .withStatus (jpa.getStatus ())
            .withSerie (serieJpaMapper.mapToDomain(jpa.getSerieJpa()))
            .withAppUser (appUserJpaMapper.mapToEntity (jpa.getAppUserjpa()))
            .build();

    return entity;
    }

    public List<ViewingSerie> mapToEntity(List<ViewingSerieJpa> jpas){
        if (jpas == null) {
            return null;
        }

        final List<ViewingSerie> viewingSeries = new ArrayList<ViewingSerie>();
        for( ViewingSerieJpa item : jpas ) {
            viewingSeries.add(mapToEntity(item));
        }
        return viewingSeries;
    }



}


