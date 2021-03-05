package fr.epita.filrouge.domain.entity.serie;

import java.util.List;

public interface SerieRepository {

     Serie findById(String id);

     Serie create(Serie serie);

    boolean deleteSerie(String imdbId);

    List<Serie> findAllSeries();

    List<Serie> findAllSeriesByPage(int numPage, int sizePage);

    long countTotalSerie();

    List<Serie> searchSerieByCriteria(SearchSerie mapDtoToDomain);
}
