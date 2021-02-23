package fr.epita.filrouge.domain.entity.movie;

import java.util.List;

public interface MovieRepository {

    void create(Movie m);
    Movie findOne(Long id);
    List<Movie> findMovieFromTitle(String movieTitle);

}
