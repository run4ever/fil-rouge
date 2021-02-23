package fr.epita.filrouge.domain.entity.movie;

import fr.epita.filrouge.domain.entity.person.AppUser;

import java.util.List;

public interface ViewingMovieRepository {

    void create(ViewingMovie vm);
    List<ViewingMovie> findViewingMovieFromUser(AppUser appUser);
    List<ViewingMovie> findViewingMovieFromUserLastname(String lastname);
    List<ViewingMovie> findAllViewingMovie();
    void delete(ViewingMovie vm);

}
