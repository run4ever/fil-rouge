package fr.epita.filrouge.domain.entity.serie;

import java.util.List;

public interface SerieRepositoryExternal {
    Serie searchByApiSerieId(String apiSerieId);
    List<Serie> searchByTitle(String title);
}
