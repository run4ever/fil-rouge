package fr.epita.filrouge.application.movie;

import java.util.List;

public interface MovieService {

    void createMovieService(MovieDto movieDto);
    MovieDto getOneMovieService(String apiMovieId);
    List<MovieDto> listAllMoviesService();
    List<MovieDto> searchExternalMovie(String title);
    MovieDto getExternalMovie(String apiMovieId);
}
