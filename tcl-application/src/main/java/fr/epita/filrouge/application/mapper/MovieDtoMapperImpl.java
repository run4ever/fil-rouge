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

        Movie movie = new Movie();

        movie.setTitle( movieDto.getTitle() );
        movie.setDescription( movieDto.getDescription() );
        movie.setImageUrl( movieDto.getImageUrl() );
        movie.setImdbId( movieDto.getImdbId() );
        movie.setDuration( movieDto.getDuration() );
        movie.setReleaseDate( movieDto.getReleaseDate() );
        movie.setActors( movieDto.getActors() );
        movie.setCategory( movieDto.getCategory() );
        movie.setPublicNotation(new PublicNotation(movieDto.getAverageRating(), movieDto.getNumberOfVotes()));

        return movie;
    }

    public List<Movie> mapDtoToDomain(List<MovieDto> movieDtoList) {
        if ( movieDtoList == null ) {
            return null;
        }

        List<Movie> list = new ArrayList<>( movieDtoList.size() );
        for ( MovieDto movieDto : movieDtoList ) {
            list.add( mapDtoToDomain( movieDto ) );
        }

        return list;
    }

    public MovieDto mapDomainToDto(Movie movie) {
        if ( movie == null ) {
            return null;
        }

        MovieDto movieDto = new MovieDto();

        movieDto.setImdbId( movie.getImdbId() );
        movieDto.setDuration( movie.getDuration() );
        movieDto.setReleaseDate( movie.getReleaseDate() );
        movieDto.setActors( movie.getActors() );
        movieDto.setCategory( movie.getCategory() );
        movieDto.setTitle( movie.getTitle() );
        movieDto.setDescription( movie.getDescription() );
        movieDto.setImageUrl( movie.getImageUrl() );
        movieDto.setAverageRating(movie.getPublicNotation().getAverageRating());
        movieDto.setNumberOfVotes(movie.getPublicNotation().getNumberOfVotes());

        return movieDto;
    }

    public List<MovieDto> mapDomainToDto(List<Movie> movieList) {
        if ( movieList == null ) {
            return null;
        }

        List<MovieDto> list = new ArrayList<>( movieList.size() );
        for ( Movie movie : movieList ) {
            list.add( mapDomainToDto( movie ) );
        }

        return list;
    }


}
