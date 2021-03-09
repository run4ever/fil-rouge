package fr.epita.filrouge.domain.entity.viewingserie;

import java.util.List;

public interface ViewingSerieRepository {

    ViewingSerie create(ViewingSerie serieView);

    ViewingSerie findById (String id);

    List<ViewingSerie> findAllVievingSerie();


    ViewingSerie findByIdUserAndIdSerie(String email, String imdb);

    List<ViewingSerie> findallViewingSerieByUser(String email);

    List<ViewingSerie> findallViewingSerieByUserByPage(String email, int offset, int limit, String sortAttribute, boolean sortAsc);

    long countTotalViewingSerieByUser(String email);

    void delete(ViewingSerie vs);

    ViewingSerie update(ViewingSerie vs);

    ViewingSerie findViewingSerieFromUserEmailAndSerieId(String email, String serieId);
}
