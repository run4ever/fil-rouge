package fr.epita.filrouge.domain.entity.movie;

import fr.epita.filrouge.domain.entity.person.AppUser;

import java.util.List;

public interface ViewingMovieRepository {

    ViewingMovie create(ViewingMovie vm);
    ViewingMovie findViewingMovieFromUserEmailAndMovieId(String email, String movieId);
    List<ViewingMovie> findViewingMovieFromUser(AppUser appUser);
    List<ViewingMovie> findViewingMovieFromUserEmail(String email);
    List<ViewingMovie> findAllViewingMovie();
    void delete(ViewingMovie vm);
    ViewingMovie update(ViewingMovie vm);
}
