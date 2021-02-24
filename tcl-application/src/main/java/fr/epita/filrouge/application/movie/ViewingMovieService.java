package fr.epita.filrouge.application.movie;

import fr.epita.filrouge.domain.entity.movie.Movie;
import fr.epita.filrouge.domain.entity.movie.ViewingMovie;
import fr.epita.filrouge.domain.entity.person.AppUser;

import java.util.List;


public interface ViewingMovieService {
    /** ajouter un Movie dans le Visonnage(ViewingMovie) avec AppUser et Movie */
    void addMovieToViewingMovie(AppUser appUser, Movie movie);

    /** Retourne la liste des Movie dans Visonnage(ViewingMovie) de AppUser */
    List<ViewingMovie> getViewingMovie(AppUser appUser);
}
