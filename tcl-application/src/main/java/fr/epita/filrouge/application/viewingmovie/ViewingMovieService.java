package fr.epita.filrouge.application.viewingmovie;

import fr.epita.filrouge.application.movie.MovieDto;
import fr.epita.filrouge.application.person.AppUserDto;


import java.util.List;


public interface ViewingMovieService {
    /** ajouter un Movie dans le Visonnage(ViewingMovie) avec AppUser et Movie */
    ViewingMovieCreateDto addMovieToViewingMovie(ViewingMovieCreateDto viewingMovieCreateDto);

    /** Retourne la liste des Movie dans Visonnage(ViewingMovie) de AppUser */
    List<ViewingMovieRestitDto> getViewingMovie(AppUserDto appUserDto);
    List<ViewingMovieRestitDto> getViewingMovieByUserEmail(String email);

    ViewingMovieCreateDto updateViewingMovieStatus(ViewingMovieCreateDto viewingMovieCreateDto);
    void deleteViewingMovie(ViewingMovieCreateDto viewingMovieCreateDto);
    ViewingMovieRestitDto verifyViewingMovieExistence(MovieDto movie, String email);

}
