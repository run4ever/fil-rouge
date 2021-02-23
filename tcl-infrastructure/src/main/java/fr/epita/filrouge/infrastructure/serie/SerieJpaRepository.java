package fr.epita.filrouge.infrastructure.serie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SerieJpaRepository extends JpaRepository<SerieJpa,Long>{

  SerieJpa save(SerieJpa serie);

  SerieJpa findByImdbId(String id);
  void deleteByImdbId(String id);
  List<SerieJpa> findByImdbIdNotNull();



}
