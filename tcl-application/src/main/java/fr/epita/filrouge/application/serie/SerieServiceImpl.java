package fr.epita.filrouge.application.serie;

import fr.epita.filrouge.application.common.PageDTO;

import fr.epita.filrouge.application.mapper.SearchSerieDtoMapper;
import fr.epita.filrouge.application.mapper.SerieDtoMapper;
import fr.epita.filrouge.domain.entity.serie.Serie;
import fr.epita.filrouge.domain.entity.serie.SerieRepository;
import fr.epita.filrouge.domain.entity.serie.SerieRepositoryExternal;
import fr.epita.filrouge.domain.exception.*;
import fr.epita.filrouge.domain.exception.AlreadyExistingException;
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

    @Autowired
    private SerieRepositoryExternal serieRepositoryExternal;

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
        logger.info ("numéro de page : " + Integer.toString (calculPage (offset, limit)));
        logger.info ("taille de la page : " + Integer.toString (limit-offset));
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
        logger.info ("taille de la liste retournée : " + Integer.toString(pageDTO.getListViewingOrSerieOrVideo ().size ()));
        return pageDTO;
    }

    @Override
    public List<SerieDto> findAllSeries() {

        List<Serie> serieList = serieRepository.findAllSeries ();
        List<SerieDto> serieDtoList = new ArrayList<SerieDto> ();
        if (serieList.isEmpty ()) {
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
        if (serieList.isEmpty ()) {
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

        if (offset == 0) return 0;
        if (limit <= offset)  {
            throw new PaginationException ("offset supérieur ou 1égal à limit", ErrorCodes.PAGINATION_ERROR);
        }
        if (offset%(limit-offset) != 0) {
            throw new PaginationException ("offset incohérent", ErrorCodes.PAGINATION_ERROR);
        }
        else return offset/(limit-offset);
    }

    @Override
    public List<SerieDto> searchExternalSerie(String title) {
        return serieDtoMapper.mapDomainToDto(serieRepositoryExternal.searchByTitle(title));
    }

    @Override
    public SerieDto getExternalSerie(String apiSerieId) {
        return serieDtoMapper.mapDomainToDto(serieRepositoryExternal.searchByApiSerieId(apiSerieId));
    }

}



