package fr.epita.filrouge.infrastructure.mapper;

import fr.epita.filrouge.domain.entity.viewingserie.ViewingSerie;
import fr.epita.filrouge.infrastructure.viewingserie.ViewingSerieJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
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
                .withCurrentSeason (entity.getCurrentSeason ())
                .withCurrentEpisode (entity.getCurrentEpisode ())
                .withStatus (entity.getStatus ())
                .withSerieJpa(serieJpaMapper.mapToJpa (entity.getSerie ()))
                .withAppUserjpa (appUserJpaMapper.mapToJpa (entity.getAppUser ()))
                .build ();
    }

    public List<ViewingSerieJpa> mapToJpa(List<ViewingSerie> entities) {
        if (entities == null) {
            return Collections.emptyList ();
        }
        final List<ViewingSerieJpa> viewingSeriesJpa = new ArrayList<ViewingSerieJpa> ();
        for (ViewingSerie item : entities) {
            viewingSeriesJpa.add (mapToJpa (item));
        }
        return viewingSeriesJpa;
    }

    public ViewingSerie mapToDomain(ViewingSerieJpa jpa){
        if (jpa == null) {
            return null;
        }

        return ViewingSerie.Builder.aViewingSerie ()
                .withStatus(jpa.getStatus())
                .withCurrentSeason (jpa.getCurrentSeason ())
                .withCurrentEpisode (jpa.getCurrentEpisode ())
                .withStatus (jpa.getStatus ())
                .withSerie (serieJpaMapper.mapToDomain(jpa.getSerieJpa()))
                .withAppUser (appUserJpaMapper.mapToEntity (jpa.getAppUserJpa ()))
                .withDateLastAction (convertToLocalDateViaInstant(jpa.getDateLastAction ()))
                .build();

    }

    public List<ViewingSerie> mapToDomain(List<ViewingSerieJpa> jpas){
        if (jpas == null) {
            return Collections.emptyList ();
        }

        final List<ViewingSerie> viewingSeries = new ArrayList<ViewingSerie>();
        for( ViewingSerieJpa item : jpas ) {
            viewingSeries.add(mapToDomain(item));
        }
        return viewingSeries;
    }

  private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

}
