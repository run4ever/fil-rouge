package fr.epita.filrouge.infrastructure.mapper;

import fr.epita.filrouge.domain.entity.common.PublicNotation;
import fr.epita.filrouge.domain.entity.movie.Movie;
import fr.epita.filrouge.infrastructure.movie.MovieJpa;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieJpaMapper {

    public MovieJpa mapToJpa(Movie entity){
        if (entity == null) {
            return null;
        }
        return MovieJpa.Builder.aMovieJpa()
                .withId(entity.getId())
                .withTitle(entity.getTitle())
                .withDescription(entity.getDescription())
                .withCategory(entity.getCategory())
                .withDuration(entity.getDuration())
                .withImageUrl(entity.getImageUrl())
                .withReleaseDate(entity.getReleaseDate())
                .withActors(entity.getActors())
                .withAverageRating(entity.getPublicNotation().getAverageRating())
                .withNumberOfVotes(entity.getPublicNotation().getNumberOfVotes())
                .build();
    }

    public List<MovieJpa> mapToJpa(List<Movie> entities){
        if (entities == null) {
            return null;
        }
        final List<MovieJpa> moviesJpa = new ArrayList<MovieJpa>();
        for( Movie item : entities ) {
            moviesJpa.add(mapToJpa(item));
        }
        return moviesJpa;
    }

    public Movie mapToEntity(MovieJpa jpa){
        if (jpa == null) {
            return null;
        }

        final Movie entity = Movie.Builder.aMovie()
                .withId(jpa.getId())
                .withTitle(jpa.getTitle())
                .withDescription(jpa.getDescription())
                .withCategory(jpa.getCategory())
                .withDuration(jpa.getDuration())
                .withImageUrl(jpa.getImageUrl())
                .withReleaseDate(jpa.getReleaseDate())
                .withActors(jpa.getActors())
                .withPublicNotation(new PublicNotation(jpa.getAverageRating(),jpa.getNumberOfVotes()))
                .build();

        return entity;
    }

    public List<Movie> mapToEntity(List<MovieJpa> jpas){
        if (jpas == null) {
            return null;
        }
        final List<Movie> movies = new ArrayList<Movie>();
        for( MovieJpa item : jpas ) {
            movies.add(mapToEntity(item));
        }
        return movies;
    }

}
