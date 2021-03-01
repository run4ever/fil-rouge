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

    PageDTO findAllSeriesByPage(int offset, int limit);

    List<SerieDto> findAllSeries();

 //   PageDTO searchAllSeries(SearchSerieDto searchSerieDto);
}

