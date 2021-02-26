package fr.epita.filrouge.application.serie;


import fr.epita.filrouge.domain.entity.serie.Serie;

/**
 * Liste des services pour l'objet Serie
 * @author : Yoss
 */

public interface SerieService {

    SerieDto getSerieById(String id);

    SerieDto createSerie(SerieDto serieDto);
}

