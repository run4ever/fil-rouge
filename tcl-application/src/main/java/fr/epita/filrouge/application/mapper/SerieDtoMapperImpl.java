package fr.epita.filrouge.application.mapper;

import fr.epita.filrouge.application.serie.SerieDto;
import fr.epita.filrouge.domain.entity.common.PublicNotation;
import fr.epita.filrouge.domain.entity.serie.Serie;
import org.springframework.stereotype.Component;

@Component
public class SerieDtoMapperImpl implements SerieDtoMapper {

    @Override
    public Serie mapDtoToDomain(SerieDto serieDto) {
        if ( serieDto == null ) {
            return null;
        }

        return Serie.Builder.aSerie()
                .withTitle(serieDto.getTitle())
                .withDescription(serieDto.getDescription())
                .withCategory(serieDto.getCategory())
                .withNumberOfEpisode(serieDto.getNumberOfEpisode())
                .withNumberOfSeason(serieDto.getNumberOfSeason())
                .withStartYear(serieDto.getStartYear())
                .withEndYear(serieDto.getEndYear())
                .withImdbId(serieDto.getImdbId())
                .withStatusSerie(serieDto.getStatusSerie())
                .withActors(serieDto.getActors())
                .withImageUrl(serieDto.getImageUrl())
                .withPublicNotation(new PublicNotation(serieDto.getAverageRating(), serieDto.getNumberOfVotes()))
                .build();
    }

    @Override
    public SerieDto mapDomainToDto(Serie serie) {
        if ( serie == null ) {
            return null;
        }

        return SerieDto.Builder.aSerieDto()
                .withTitle(serie.getTitle())
                .withDescription(serie.getDescription())
                .withCategory(serie.getCategory())
                .withNumberOfEpisode(serie.getNumberOfEpisode())
                .withNumberOfSeason(serie.getNumberOfSeason())
                .withStartYear(serie.getStartYear())
                .withEndYear(serie.getEndYear())
                .withImdbId(serie.getImdbId())
                .withStatusSerie(serie.getStatusSerie())
                .withActors(serie.getActors())
                .withImageUrl(serie.getImageUrl())
                .withAverageRating(serie.getPublicNotation().getAverageRating())
                .withNumberOfVotes(serie.getPublicNotation().getNumberOfVotes())
                .build();

    }
}
