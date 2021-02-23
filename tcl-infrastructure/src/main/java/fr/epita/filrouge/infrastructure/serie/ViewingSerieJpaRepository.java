package fr.epita.filrouge.infrastructure.serie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewingSerieJpaRepository extends JpaRepository<ViewingSerieJpa,Long> {

}
