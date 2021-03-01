package fr.epita.filrouge.application.viewingmovie;

import fr.epita.filrouge.application.movie.MovieDto;
import fr.epita.filrouge.application.person.AppUserDto;


import java.util.List;


public interface ViewingMovieService {
    /** ajouter un Movie dans le Visonnage(ViewingMovie) avec AppUser et Movie */
    void addMovieToViewingMovie(AppUserDto appUserDto, MovieDto movieDto);

    /** Retourne la liste des Movie dans Visonnage(ViewingMovie) de AppUser */
    List<ViewingMovieDto> getViewingMovie(AppUserDto appUserDto);
}
