package fr.epita.filrouge.domain.entity.serie;

import java.util.List;

public interface SerieRepository {

     Serie findById(String id);

     Serie create(Serie serie);

    boolean deleteSerie(String imdbId);

    List<Serie> findAllSeries();
}
