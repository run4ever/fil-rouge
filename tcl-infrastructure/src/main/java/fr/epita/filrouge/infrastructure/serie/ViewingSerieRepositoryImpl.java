package fr.epita.filrouge.infrastructure.serie;

import fr.epita.filrouge.domain.entity.serie.ViewingSerie;
import fr.epita.filrouge.domain.entity.serie.ViewingSerieRepository;
import fr.epita.filrouge.infrastructure.mapper.ViewingSerieJpaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ViewingSerieRepositoryImpl implements ViewingSerieRepository{

    private static Logger logger = LoggerFactory.getLogger(ViewingSerieRepositoryImpl.class);

    @Autowired

    private ViewingSerieJpaRepository viewingSerieJpaRepository;

    @Autowired

    private ViewingSerieJpaMapper viewingSerieJpaMapper;


    @Override
    public ViewingSerie create(ViewingSerie serieView) {

        return viewingSerieJpaMapper.mapToDomain (viewingSerieJpaRepository.save(viewingSerieJpaMapper.mapToJpa (serieView)));

    }

    @Override
    public ViewingSerie findById(String id) {
        logger.info("VievingSerie JPA impl, findById : " + id);
        return viewingSerieJpaMapper.mapToDomain (viewingSerieJpaRepository.findByIdViewSerie (id));
    }

    @Override
    public List<ViewingSerie> findAllVievingSerie() {
        return viewingSerieJpaMapper.mapToDomain (viewingSerieJpaRepository.findByIdViewSerieIsNotNull ());
    }
}
