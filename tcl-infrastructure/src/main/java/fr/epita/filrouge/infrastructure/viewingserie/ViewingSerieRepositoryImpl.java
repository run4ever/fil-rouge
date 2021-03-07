package fr.epita.filrouge.infrastructure.viewingserie;

import fr.epita.filrouge.domain.entity.viewingserie.ViewingSerie;
import fr.epita.filrouge.domain.entity.viewingserie.ViewingSerieRepository;
import fr.epita.filrouge.infrastructure.mapper.ViewingSerieJpaMapper;
import fr.epita.filrouge.infrastructure.person.AppUserJpaRepository;
import fr.epita.filrouge.infrastructure.serie.SerieJpa;
import fr.epita.filrouge.infrastructure.serie.SerieJpaRepository;
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

    @Autowired
    private AppUserJpaRepository appUserJpaRepository;

    @Autowired
    private SerieJpaRepository serieJpaRepository;

    @Override
    public ViewingSerie create(ViewingSerie serieView) {

        logger.info ("ViewgingSerie - create : " +serieView.toString ());
        ViewingSerieJpa viewingSerieJpa = viewingSerieJpaMapper.mapToJpa (serieView);
        viewingSerieJpa.setAppUserJpa (appUserJpaRepository.findByEmail (serieView.getAppUser ().getEmail ()));
        viewingSerieJpa.setSerieJpa (serieJpaRepository.findByImdbId (serieView.getSerie ().getImdbId ()));
        return viewingSerieJpaMapper.mapToDomain (viewingSerieJpaRepository.save(viewingSerieJpa));

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

    @Override
    public void delete(ViewingSerie vs) {
        ViewingSerieJpa viewingSerieJpa = viewingSerieJpaRepository.findByAppUserJpa_EmailAndSerieJpa_ImdbId (
                vs.getAppUser ().getEmail (), vs.getSerie ().getImdbId ());
        ViewingSerieJpa viewingSerieJpaDeleted = viewingSerieJpaMapper.mapToJpa (vs);
        viewingSerieJpaDeleted.setId (viewingSerieJpa.getId ());
       viewingSerieJpaRepository.delete (viewingSerieJpaDeleted);
    }

    @Override
    public ViewingSerie update(ViewingSerie vs) {
        ViewingSerieJpa viewingSerieJpa = viewingSerieJpaRepository.findByAppUserJpa_EmailAndSerieJpa_ImdbId (
                vs.getAppUser ().getEmail (), vs.getSerie ().getImdbId ());
        ViewingSerieJpa viewingSerieJpaUpdated = viewingSerieJpaMapper.mapToJpa (vs);
        viewingSerieJpaUpdated.setId (viewingSerieJpa.getId ());
        viewingSerieJpaUpdated.setAppUserJpa (viewingSerieJpa.getAppUserJpa ());
        viewingSerieJpaUpdated.setSerieJpa (viewingSerieJpa.getSerieJpa ());
        viewingSerieJpaRepository.save(viewingSerieJpaUpdated);
        return vs;
    }
}
