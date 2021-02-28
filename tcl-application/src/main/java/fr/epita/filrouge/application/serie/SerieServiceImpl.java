package fr.epita.filrouge.application.serie;

import fr.epita.filrouge.application.common.PageDTO;

import fr.epita.filrouge.application.mapper.SearchSerieDtoMapper;
import fr.epita.filrouge.application.mapper.SerieDtoMapper;
import fr.epita.filrouge.domain.entity.serie.Serie;
import fr.epita.filrouge.domain.entity.serie.SerieRepository;
import fr.epita.filrouge.domain.exception.AlreadyExistingException;
import fr.epita.filrouge.domain.exception.ErrorCodes;
import fr.epita.filrouge.domain.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SerieServiceImpl implements SerieService {

    private static Logger logger = LoggerFactory.getLogger(SerieServiceImpl.class);

    @Autowired
    private SerieRepository serieRepository;

    @Autowired
    private SerieDtoMapper serieDtoMapper;

    @Autowired
    private SearchSerieDtoMapper searchSerieDtoMapper;


    @Override
    public SerieDto getSerieById(String id) {

        Serie serie = serieRepository.findById (id);
        if (serie == null) {
            throw new NotFoundException ("Serie not existing : " + id, ErrorCodes.SERIE_NOT_FOUND);
        }
        return serieDtoMapper.mapDomainToDto (serie);
    }

    @Override
    public SerieDto createSerie(SerieDto serieDto) {

        if (serieRepository.findById (serieDto.getImdbId ()) != null) {
            throw new AlreadyExistingException ("Serie existing : " + serieDto.getImdbId (), ErrorCodes.SERIE_ALREADY_EXISTING);
        }
        return serieDtoMapper.mapDomainToDto (serieRepository.create (serieDtoMapper.mapDtoToDomain (serieDto)));
    }

    @Override
    public boolean deleteSerie(String id) {
        if(serieRepository.findById (id) == null) {
            throw new NotFoundException ("Serie not existing : " + id, ErrorCodes.SERIE_NOT_FOUND);
        }
        return serieRepository.deleteSerie (id);
    }

    /**
     * Méthode qui restitue par page la liste des séries.
     * @param offset
     * @param limit
     * @return
     */
    @Override
    public PageDTO findAllSeriesByPage(int offset, int limit) {

        List<SerieDto> serieDtoList = new ArrayList<SerieDto> ();
        for (Serie serie: serieRepository.findAllSeriesByPage (calculPage (offset, limit),limit-offset)){
            serieDtoList.add (serieDtoMapper.mapDomainToDto (serie));
        }
        PageDTO pageDTO = new PageDTO ();
        pageDTO.setLimit (limit);
        pageDTO.setOffset (offset);
        pageDTO.setSortAttribute ("title");
        pageDTO.setTotal (serieRepository.countTotalSerie());
        pageDTO.setListViewingOrSerieOrVideo (serieDtoList);
        pageDTO.setSortAsc (true);
        return pageDTO;
    }

    @Override
    public List<SerieDto> findAllSeries() {

        List<Serie> serieList = serieRepository.findAllSeries ();
        List<SerieDto> serieDtoList = new ArrayList<SerieDto> ();
        if (serieList.size () == 0) {
            throw new NotFoundException ("Aucune série présente en base", ErrorCodes.SERIE_NOT_FOUND);
        }else {
            for (Serie serie: serieList){
                serieDtoList.add (serieDtoMapper.mapDomainToDto (serie));
            }
        }
        return serieDtoList;
    }

    @Override
    public PageDTO searchAllSeries(SearchSerieDto searchSerieDto) {

        List<SerieDto> serieDtoList = new ArrayList<SerieDto> ();
        List<Serie> serieList = serieRepository.searchSerieByCriteria(searchSerieDtoMapper.mapDtoToDomain (searchSerieDto));
        if (serieList.size () == 0) {
            throw new NotFoundException ("Aucune série trouvée suite à la recherche en base", ErrorCodes.SERIE_NOT_FOUND);
        }else {
            for (Serie serie: serieList){
                serieDtoList.add (serieDtoMapper.mapDomainToDto (serie));
            }
        }
        PageDTO pageDTO = new PageDTO ();
        pageDTO.setLimit (searchSerieDto.getLimit ());
        pageDTO.setOffset (searchSerieDto.getOffset ());
        pageDTO.setSortAttribute ("title");
        pageDTO.setTotal (serieRepository.countTotalSerie());
        pageDTO.setListViewingOrSerieOrVideo (serieDtoList);
        pageDTO.setSortAsc (true);
        return pageDTO;
    }

    private int calculPage(int offset, int limit) {
        if (limit == offset) return offset;
        else return offset/(limit-offset);
    }
}





