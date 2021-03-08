package fr.epita.filrouge.infrastructure.viewingmovie;

import fr.epita.filrouge.domain.entity.viewingmovie.ViewingMovie;
import fr.epita.filrouge.domain.entity.viewingmovie.ViewingMovieRepository;
import fr.epita.filrouge.domain.entity.person.AppUser;
import fr.epita.filrouge.infrastructure.mapper.AppUserJpaMapper;
import fr.epita.filrouge.infrastructure.mapper.ViewingMovieJpaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ViewingMovieRepositoryImpl implements ViewingMovieRepository {

    @Autowired
    private ViewingMovieJpaRepository viewingMovieJpaRepository;

    @Autowired
    private ViewingMovieJpaMapper viewingMovieJpaMapper;

    @Autowired
    private AppUserJpaMapper appUserJpaMapper;

    @Override
    public ViewingMovie create(ViewingMovie vm) {
        viewingMovieJpaRepository.save(viewingMovieJpaMapper.mapToJpa(vm));
        return vm;
    }

    @Override
    public ViewingMovie findViewingMovieFromUserEmailAndMovieId(String email, String movieId) {
        return viewingMovieJpaMapper.mapToEntity(viewingMovieJpaRepository.findByAppUserJpaEmailAndMovieJpaImdbId(email, movieId));
    }

    @Override
    public List<ViewingMovie> findViewingMovieFromUser(AppUser appUser) {
        return viewingMovieJpaMapper.mapToEntity(viewingMovieJpaRepository.findByAppUserJpa(appUserJpaMapper.mapToJpa(appUser)));
    }

    @Override
    public List<ViewingMovie> findViewingMovieFromUserEmail(String email) {
        return viewingMovieJpaMapper.mapToEntity(viewingMovieJpaRepository.findByAppUserJpaEmail(email));
    }

    @Override
    public List<ViewingMovie> findAllViewingMovie() {
        return viewingMovieJpaMapper.mapToEntity(viewingMovieJpaRepository.findAll());
    }

    @Override
    public void delete(ViewingMovie vm) {
        viewingMovieJpaRepository.delete(viewingMovieJpaMapper.mapToJpa(vm));
    }

    @Override
    public ViewingMovie update(ViewingMovie vm) {
        viewingMovieJpaRepository.save(viewingMovieJpaMapper.mapToJpa(vm));
        return vm;
    }
}
