package fr.epita.filrouge.infrastructure.serie;

import fr.epita.filrouge.domain.entity.serie.Serie;
import fr.epita.filrouge.domain.entity.serie.ISerie;
import fr.epita.filrouge.infrastructure.mapper.SerieJpaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SerieRepositoryImpl implements ISerie {

    private static Logger logger = LoggerFactory.getLogger(SerieRepositoryImpl.class);

    @Autowired
    private SerieJpaMapper serieJpaMapper;

    @Autowired
    private ISerieRepository iSerieRepository;





    @Override
    public Serie findById(String id) {
        logger.info("Serie JPA impl, findById : " + id);
        return serieJpaMapper.mapToDomain (iSerieRepository.findByImdbId (id));
    }

    @Override
    public Serie create(Serie serie) {
        return serieJpaMapper.mapToDomain (iSerieRepository.save (serieJpaMapper.mapToJpa (serie)));
    }
}
