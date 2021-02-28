package fr.epita.filrouge.infrastructure.mapper;

import fr.epita.filrouge.domain.entity.serie.Serie;
import fr.epita.filrouge.infrastructure.serie.SerieJpa;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class SerieJpaMapper {



    public SerieJpa mapToJpa(Serie entity) {
        if (entity == null) {
            return null;
        }

        return SerieJpa.Builder.aSerieJpa ()
                .withImdbId (entity.getImdbId ())
                .withTitle (entity.getTitle ())
                .withDescription (entity.getDescription ())
                .withStartYear (entity.getStartYear ())
                .withEndYear (entity.getEndYear ())
                .withNumberOfSeason (entity.getNumberOfSeason ())
                .withNumberOfEpisode (entity.getNumberOfEpisode ())
                .withCategory (entity.getCategory ())
                .withStatusSerie (entity.getStatusSerie ())
                .build ();

    }

    public List<SerieJpa> mapToJpa(List<Serie> entities) {
        if (entities == null) {
            return Collections.emptyList ();
        }
        final List<SerieJpa> seriesJpa = new ArrayList<SerieJpa> ();
        for (Serie item : entities) {
            seriesJpa.add (mapToJpa (item));
        }
        return seriesJpa;
    }

    public Serie mapToDomain(SerieJpa jpa) {
        if (jpa == null) {
            return null;
        }


        return Serie.Builder.aSerie ()

                .withImdbId (jpa.getImdbId ())
                .withTitle (jpa.getTitle ())
                .withDescription (jpa.getDescription ())
                .withStartYear (jpa.getStartYear ())
                .withEndYear (jpa.getEndYear ())
                .withNumberOfSeason (jpa.getNumberOfSeason ())
                .withNumberOfEpisode (jpa.getNumberOfEpisode ())
                .withCategory (jpa.getCategory ())
                .withStatusSerie (jpa.getStatusSerie ())
                .build ();



    }

    public List<Serie> mapToDomain(List<SerieJpa> jpas) {
        if (jpas == null) {
            return Collections.emptyList ();
        }
        final List<Serie> series = new ArrayList<Serie> ();
        for (SerieJpa item : jpas) {
            series.add (mapToDomain (item));
        }
        return series;
    }

}









