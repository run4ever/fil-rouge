package fr.epita.filrouge.infrastructure.serie;

import fr.epita.filrouge.domain.entity.serie.SearchSerie;
import fr.epita.filrouge.domain.entity.serie.Serie;
import fr.epita.filrouge.domain.entity.serie.SerieRepository;
import fr.epita.filrouge.infrastructure.exception.TechnicalException;
import fr.epita.filrouge.infrastructure.exception.TechnicalExceptionEnum;
import fr.epita.filrouge.infrastructure.mapper.SerieJpaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SerieRepositoryImpl implements SerieRepository {

    private static Logger logger = LoggerFactory.getLogger(SerieRepositoryImpl.class);

    @Autowired
    private SerieJpaMapper serieJpaMapper;

    @Autowired
    private SerieJpaRepository iSerieRepository;

    @Override
    public Serie findById(String id) {
        logger.info("Serie JPA impl, findById : " + id);
        try {
            return serieJpaMapper.mapToDomain (iSerieRepository.findByImdbId (id));
        }
        catch (DataAccessException e) {
            logger.error ("erreur lors de la récupération de la série, imdbId :" + id);
            throw new TechnicalException (TechnicalExceptionEnum.JPA_READ_ACCESS, "erreur lors de la récupération de la série");
        }

    }

    @Override
    public Serie create(Serie serie) {
        try {
            return serieJpaMapper.mapToDomain (iSerieRepository.save (serieJpaMapper.mapToJpa (serie)));
        }
        catch (DataAccessException e) {
            logger.error ("erreur lors de la création de la série, imdbId :" + serie.getImdbId ());
            throw new TechnicalException (TechnicalExceptionEnum.JPA_CREATE_ACCESS, "erreur lors de la création de la série");
        }
    }

    @Override
    public boolean deleteSerie(String imdbId) {
        try {
            iSerieRepository.deleteByImdbId (imdbId);
            return true;
        }
        catch (DataAccessException e){
            logger.error ("erreur lors de la suppression de la série, imdbId :" +imdbId);
            throw new TechnicalException (TechnicalExceptionEnum.JPA_DELETE_ACCESS, "erreur lors de la suppression de la série");
        }
    }

    @Override
    public List<Serie> findAllSeries() {
        try {
            return serieJpaMapper.mapToDomain(iSerieRepository.findByImdbIdNotNull ());
        }
        catch (DataAccessException e){
            logger.error ("erreur lors de la récupération de l'ensemble des séries");
            throw new TechnicalException (TechnicalExceptionEnum.JPA_READ_ACCESS, "erreur lors de la récupération de l'ensemble des séries");
        }
    }

    /**
     * Récupération de la liste des séries à partir du numéro de la page et sa taille
     * @param numPage
     * @param sizePage
     * @return
     */
    @Override
    public List<Serie> findAllSeriesByPage(int numPage, int sizePage) {
        try {
            return serieJpaMapper.mapToDomain (iSerieRepository.findByImdbIdNotNull (PageRequest.of (numPage, sizePage, Sort.by("title").ascending ())));
        }
        catch (DataAccessException e){
            logger.error ("erreur lors de la récupération de l'ensemble des séries");
            throw new TechnicalException (TechnicalExceptionEnum.JPA_READ_ACCESS, "erreur lors de la récupération de l'ensemble des séries");
        }
    }

    @Override
    public long countTotalSerie() {
        return iSerieRepository.count ();
    }

    @Override
    public List<Serie> searchSerieByCriteria(SearchSerie mapDtoToDomain) {
        return serieJpaMapper.mapToDomain(iSerieRepository.findByImdbIdNotNull ());
    }

}
