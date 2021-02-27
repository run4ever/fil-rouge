package fr.epita.filrouge.infrastructure.viewingserie;

import fr.epita.filrouge.domain.entity.viewingserie.ViewingSerie;
import fr.epita.filrouge.domain.entity.viewingserie.ViewingSerieRepository;
import fr.epita.filrouge.infrastructure.mapper.ViewingSerieJpaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @Override
    public ViewingSerie findByIdUserAndIdSerie(String email, String imdb) {
        return viewingSerieJpaMapper.mapToDomain (viewingSerieJpaRepository.findByAppUserJpa_EmailAndSerieJpa_ImdbId (email, imdb));

    }

    @Override
    public List<ViewingSerie> findallViewingSerieByUser(String email) {
        return viewingSerieJpaMapper.mapToDomain (viewingSerieJpaRepository.findByAppUserJpa_Email (email));
    }

    @Override
    public List<ViewingSerie> findallViewingSerieByUserByPage(String email, int offset, int limit, String sortAttribute, boolean sortAsc) {
        int sizePage = limit - offset;
        if (sortAsc){
             return viewingSerieJpaMapper.mapToDomain (
                     viewingSerieJpaRepository.findByIdViewSerieIsNotNull (
                             PageRequest.of(offset, sizePage, Sort.by(sortAttribute).ascending ())));
         } else {
             return viewingSerieJpaMapper.mapToDomain (
                     viewingSerieJpaRepository.findByIdViewSerieIsNotNull (
                             PageRequest.of(offset, sizePage, Sort.by(sortAttribute).descending ())));
         }
    }

    @Override
    public long countTotalViewingSerieByUser(String email) {
        return viewingSerieJpaRepository.countByAppUserJpa_Email (email);
    }
}
