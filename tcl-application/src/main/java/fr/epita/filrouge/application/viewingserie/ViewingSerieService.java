package fr.epita.filrouge.application.viewingserie;

import fr.epita.filrouge.domain.entity.serie.ViewingSerie;

import java.util.List;

public interface ViewingSerieService {

    void create(ViewingSerie serieView);

    List<ViewingSerie> findAllVievingSerie();

    ViewingSerie findById (String id);
}
