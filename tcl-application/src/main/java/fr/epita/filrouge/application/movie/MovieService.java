package fr.epita.filrouge.application.movie;

import java.util.List;

public interface MovieService {

    void createMovieService(MovieDto movieDto);
    MovieDto getOneMovieService(String apiMovieId);
    List<MovieDto> listAllMoviesService();
    Integer searchExternalMovieNbResults(String title);
    List<MovieDto> searchExternalMovie(String title, Integer pageNum);
    MovieDto getExternalMovie(String apiMovieId);
}
