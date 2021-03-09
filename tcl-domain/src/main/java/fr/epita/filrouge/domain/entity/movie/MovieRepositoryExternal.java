package fr.epita.filrouge.domain.entity.movie;

import java.util.List;

public interface MovieRepositoryExternal {
    Movie searchByApiMovieId(String apiMovieId);
    Integer getApiSearchNbResults(String title);
    List<Movie> searchByTitle(String title, Integer pageNum);
}
