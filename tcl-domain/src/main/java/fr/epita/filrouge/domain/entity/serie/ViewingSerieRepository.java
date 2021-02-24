package fr.epita.filrouge.domain.entity.serie;

import java.util.List;

public interface ViewingSerieRepository {

    ViewingSerie create(ViewingSerie serieView);

    ViewingSerie findById (String id);

    List<ViewingSerie> findAllVievingSerie();
}
