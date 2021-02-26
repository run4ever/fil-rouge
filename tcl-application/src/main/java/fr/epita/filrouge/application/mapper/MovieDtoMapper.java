package fr.epita.filrouge.application.mapper;

import fr.epita.filrouge.application.movie.MovieDto;
import fr.epita.filrouge.domain.entity.common.PublicNotation;
import fr.epita.filrouge.domain.entity.movie.Movie;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieDtoMapper {

    public MovieDto mapToDto(Movie entity){
        if (entity == null) {
            return null;
        }
        return MovieDto.Builder.aMovieDto()
                .withImdbId(entity.getImdbId())
                .withTitle(entity.getTitle())
                .withDescription(entity.getDescription())
                .withCategory(entity.getCategory())
                .withReleaseDate(entity.getReleaseDate())
                .withAverageRating(entity.getPublicNotation().getAverageRating())
                .withNumberOfVotes(entity.getPublicNotation().getNumberOfVotes())
                .withDuration(entity.getDuration())
                .withActors(entity.getActors())
                .withImageUrl(entity.getImageUrl())
                .build();
    }

    public List<MovieDto> mapToDto(List<Movie> entities){
        if (entities == null) {
            return null;
        }
        final List<MovieDto> movieDtoList = new ArrayList<>();
        for( Movie item : entities ) {
            movieDtoList.add(mapToDto(item));
        }
        return movieDtoList;
    }

    public Movie mapToEntity(MovieDto movieDto){
        if (movieDto == null) {
            return null;
        }

        return Movie.Builder.aMovie()
                .withImdbId(movieDto.getImdbId())
                .withTitle(movieDto.getTitle())
                .withDescription(movieDto.getDescription())
                .withCategory(movieDto.getCategory())
                .withReleaseDate(movieDto.getReleaseDate())
                .withPublicNotation(new PublicNotation(movieDto.getAverageRating(), movieDto.getNumberOfVotes()))
                .withDuration(movieDto.getDuration())
                .withActors(movieDto.getActors())
                .withImageUrl(movieDto.getImageUrl())
                .build();
    }

    public List<Movie> mapToEntity(List<MovieDto> movieDtoList){
        if (movieDtoList == null) {
            return null;
        }
        final List<Movie> movies = new ArrayList<>();
        for( MovieDto item : movieDtoList ) {
            movies.add(mapToEntity(item));
        }
        return movies;
    }

}
