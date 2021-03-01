package fr.epita.filrouge.application.viewingserie;

import fr.epita.filrouge.application.common.PageDTO;

import java.util.List;

public interface ViewingSerieService {

    ViewingSerieCreateDto create(ViewingSerieCreateDto serieViewDto);

    List<ViewingSerieRestitDto> findByUserAllVievingSerieDto(String email);

    PageDTO findByUserAllVievingSerieDtoByPage(String email, int offset, int limit, String sortAttribute, boolean sortAsc);

    ViewingSerieRestitDto findViewing (String email, String imdb);

}
