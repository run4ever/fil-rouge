package fr.epita.filrouge.infrastructure.mapper;

import fr.epita.filrouge.domain.entity.common.PublicNotation;
import fr.epita.filrouge.domain.entity.serie.Serie;
import fr.epita.filrouge.infrastructure.serie.SerieJpa;
import io.swagger.models.auth.In;
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

        Double averageRating = 0D;
        if(entity.getPublicNotation()!=null) {
            averageRating = entity.getPublicNotation().getAverageRating();
        }
        Integer numberOfVotes = 0;
        if(entity.getPublicNotation()!=null) {
            numberOfVotes = entity.getPublicNotation().getNumberOfVotes();
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
                .withActors(entity.getActors())
                .withImageUrl(entity.getImageUrl())
                .withAverageRating(averageRating)
                .withNumberOfVotes(numberOfVotes)
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

        //éviter plantage
        PublicNotation publicNotation;
        if(jpa.getAverageRating()!=null && jpa.getNumberOfVotes()!=null) {
            publicNotation = new PublicNotation(jpa.getAverageRating(),jpa.getNumberOfVotes());
        }
        else
        {
            publicNotation = new PublicNotation(0D,0);
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
                .withActors(jpa.getActors())
                .withImageUrl(jpa.getImageUrl())
                .withPublicNotation(publicNotation)
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
