package fr.epita.filrouge.application.mapper;

import fr.epita.filrouge.application.movie.MovieDto;
import fr.epita.filrouge.domain.entity.common.PublicNotation;
import fr.epita.filrouge.domain.entity.movie.Movie;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieDtoMapperImpl implements MovieDtoMapper {

    public Movie mapDtoToDomain(MovieDto movieDto) {
        if ( movieDto == null ) {
            return null;
        }

        return Movie.Builder.aMovie()
                .withTitle(movieDto.getTitle())
                .withDescription(movieDto.getDescription())
                .withImageUrl(movieDto.getImageUrl())
                .withImdbId(movieDto.getImdbId())
                .withReleaseDate(movieDto.getReleaseDate())
                .withDuration(movieDto.getDuration())
                .withActors(movieDto.getActors())
                .withCategory(movieDto.getCategory())
                .withPublicNotation(new PublicNotation(movieDto.getAverageRating(), movieDto.getNumberOfVotes()))
                .build();
    }

    public List<Movie> mapDtoToDomain(List<MovieDto> movieDtoList) {
        if ( movieDtoList == null ) {
            return null;
        }

        List<Movie> list = new ArrayList<>( movieDtoList.size());
        for ( MovieDto movieDto : movieDtoList ) {
            list.add( mapDtoToDomain( movieDto ));
        }

        return list;
    }

    public MovieDto mapDomainToDto(Movie movie) {
        if ( movie == null ) {
            return null;
        }

        return MovieDto.Builder.aMovieDto()
                .withImdbId(movie.getImdbId())
                .withReleaseDate(movie.getReleaseDate())
                .withDuration(movie.getDuration())
                .withActors(movie.getActors())
                .withCategory(movie.getCategory())
                .withTitle(movie.getTitle())
                .withDescription(movie.getDescription())
                .withImageUrl(movie.getImageUrl())
                .withAverageRating(movie.getPublicNotation().getAverageRating())
                .withNumberOfVotes(movie.getPublicNotation().getNumberOfVotes())
                .build();
    }

    public List<MovieDto> mapDomainToDto(List<Movie> movieList) {
        if ( movieList == null ) {
            return null;
        }

        List<MovieDto> list = new ArrayList<>( movieList.size());
        for ( Movie movie : movieList ) {
            list.add( mapDomainToDto( movie ));
        }

        return list;
    }


}
