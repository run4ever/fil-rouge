package fr.epita.filrouge.infrastructure.serie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISerieRepository extends JpaRepository<SerieJpa,Long>{

  SerieJpa save(SerieJpa serie);

  SerieJpa findByImdbId(String id);


}
