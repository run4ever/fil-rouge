package fr.epita.filrouge.application.movie;

import fr.epita.filrouge.domain.entity.movie.Movie;

public interface MovieService {

    void createMovieService(Movie movie);
    Movie getOneMovieService(String apiMovieid);

}
