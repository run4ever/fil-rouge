package fr.epita.filrouge.domain.entity.serie;

import java.util.List;

public interface SerieRepositoryExternal {
    Serie searchByApiSerieId(String apiSerieId);
    Integer getApiSearchNbResults(String title);
    List<Serie> searchByTitle(String title, Integer pageNum);
}
