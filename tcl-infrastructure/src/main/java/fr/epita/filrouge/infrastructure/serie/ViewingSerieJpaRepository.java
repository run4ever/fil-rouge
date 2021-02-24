package fr.epita.filrouge.infrastructure.serie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewingSerieJpaRepository extends JpaRepository<ViewingSerieJpa,Long> {


    ViewingSerieJpa save(ViewingSerieJpa serieView);

    ViewingSerieJpa findByIdViewSerie (String id);

    List<ViewingSerieJpa> findByIdViewSerieIsNotNull();

}
