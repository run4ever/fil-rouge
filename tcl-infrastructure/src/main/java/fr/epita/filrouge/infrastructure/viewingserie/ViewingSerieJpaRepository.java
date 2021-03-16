package fr.epita.filrouge.infrastructure.viewingserie;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.List;

@Repository
public interface ViewingSerieJpaRepository extends JpaRepository<ViewingSerieJpa,Long> {


    ViewingSerieJpa save(ViewingSerieJpa serieView);

    ViewingSerieJpa findByIdViewSerie (String id);

    List<ViewingSerieJpa> findByIdViewSerieIsNotNull(PageRequest of);

    List<ViewingSerieJpa> findByIdViewSerieIsNotNull();

    ViewingSerieJpa findByAppUserJpaEmailAndSerieJpaImdbId(String email, String imdb);

    List<ViewingSerieJpa> findByAppUserJpaEmailOrderBySerieJpaTitle(String email);

    Long countByAppUserJpa_Email(String email);

    List<ViewingSerieJpa> findBySerieJpaImdbIdAndLove(String idSerie, boolean b);
}
