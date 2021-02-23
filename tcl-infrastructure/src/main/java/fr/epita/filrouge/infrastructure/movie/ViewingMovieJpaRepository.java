package fr.epita.filrouge.infrastructure.movie;

import fr.epita.filrouge.infrastructure.person.AppUserJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViewingMovieJpaRepository extends JpaRepository<ViewingMovieJpa, Long> {

    List<ViewingMovieJpa> findByAppUserJpa(AppUserJpa appUserJpa);
    List<ViewingMovieJpa> findByAppUserJpaLastname(String lastname);

}
