package fr.epita.filrouge.domain.entity.movie;

import java.util.List;

public interface MovieRepositoryExternal {
    Movie searchByApiMovieId(String apiMovieId);
    List<Movie> searchByTitle(String title);
}
