package fr.epita.filrouge.domain.entity.movie;

import java.util.List;

public interface MovieRepository {

    void create(Movie m);
    Movie findMovieFromApiId(String id);
    List<Movie> findMovieFromTitle(String movieTitle);

}
