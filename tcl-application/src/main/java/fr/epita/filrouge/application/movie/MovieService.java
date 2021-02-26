package fr.epita.filrouge.application.movie;

import java.util.List;

public interface MovieService {

    void createMovieService(MovieDto movieDto);
    MovieDto getOneMovieService(String apiMovieid);
    List<MovieDto> listAllMovies();

}
