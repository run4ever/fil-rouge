package fr.epita.filrouge.application.viewingserie;

import fr.epita.filrouge.application.common.PageDTO;
import fr.epita.filrouge.application.serie.SerieDto;
import fr.epita.filrouge.application.viewingmovie.ViewingMovieCreateDto;

import java.util.List;

public interface ViewingSerieService {

    ViewingSerieCreateDto create(ViewingSerieCreateDto serieViewDto);

    List<ViewingSerieRestitDto> findByUserAllVievingSerieDto(String email);

    PageDTO findByUserAllVievingSerieDtoByPage(String email, int offset, int limit, String sortAttribute, boolean sortAsc);

    ViewingSerieRestitDto findViewing (String email, String imdb);

    void deleteViewingSerie(ViewingSerieCreateDto ViewingSerieCreateDto);

    ViewingSerieCreateDto updateViewingSerieStatus(ViewingSerieCreateDto viewingSerieCreateDto);

    ViewingSerieRestitDto verifyViewingSerieExistence(SerieDto item, String email);
}
