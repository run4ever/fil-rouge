package fr.epita.filrouge.infrastructure.movie;

import fr.epita.filrouge.domain.entity.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieJpaRepository extends JpaRepository<MovieJpa, Long> {
    MovieJpa findByTitle(String movieTitle);
}
