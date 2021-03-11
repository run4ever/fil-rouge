package fr.epita.filrouge.infrastructure.viewingmovie;

import fr.epita.filrouge.infrastructure.person.AppUserJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViewingMovieJpaRepository extends JpaRepository<ViewingMovieJpa, Long> {

    List<ViewingMovieJpa> findByAppUserJpa(AppUserJpa appUserJpa);
    List<ViewingMovieJpa> findByAppUserJpaEmailOrderByMovieJpaTitle(String email);
    ViewingMovieJpa findByAppUserJpaEmailAndMovieJpaImdbId(String email, String movieId);

}
