package fr.epita.filrouge.infrastructure.serie;

import fr.epita.filrouge.infrastructure.domain.entity.serie.Serie;
import fr.epita.filrouge.infrastructure.domain.entity.serie.ISerie;
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
    public Long create (Serie serie) {
        logger.trace("Ma Serie est créée : create");
        SerieJpa serieJpa = iSerieRepository.save(serieJpaMapper.mapToJpa(serie));
        return serieJpa.getId ();

    }

    @Override
    public Serie findById(Long id) {
        return serieJpaMapper.mapToDomain (iSerieRepository.findByIdSerie (id));
    }
}
