package fr.epita.filrouge.application.serie;


import fr.epita.filrouge.application.common.PageDTO;

import java.util.List;

/**
 * Liste des services pour l'objet Serie
 * @author : Yoss
 */

public interface SerieService {

    SerieDto getSerieById(String id);

    SerieDto createSerie(SerieDto serieDto);

    boolean deleteSerie(String id);

    List<SerieDto> findAllSeries();

    PageDTO searchAllSeries(SearchSerieDto searchSerieDto);

    SerieDto getExternalSerie(String apiSerieId);

    List<SerieDto> searchExternalSerie(String title, Integer pageNum);

    Integer searchExternalSerieNbResults(String title);
}

