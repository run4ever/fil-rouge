package fr.epita.filrouge.infrastructure.serie;

import fr.epita.filrouge.domain.entity.serie.Serie;
import fr.epita.filrouge.domain.entity.serie.SerieRepository;
import fr.epita.filrouge.infrastructure.mapper.SerieJpaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
        return serieJpaMapper.mapToDomain (iSerieRepository.findByImdbId (id));
    }

    @Override
    public Serie create(Serie serie) {
        return serieJpaMapper.mapToDomain (iSerieRepository.save (serieJpaMapper.mapToJpa (serie)));
    }

    @Override
    public boolean deleteSerie(String imdbId) {
        try {
            iSerieRepository.deleteByImdbId (imdbId);
            return true;
        }
        catch (Exception e){
            logger.error ("erreur lors de la suppression de la série, imdbId " +imdbId);
            e.printStackTrace ();
            return false;
        }
    }

    @Override
    public List<Serie> findAllSeries() {
        return serieJpaMapper.mapToDomain(iSerieRepository.findByImdbIdNotNull ());
    }

}
