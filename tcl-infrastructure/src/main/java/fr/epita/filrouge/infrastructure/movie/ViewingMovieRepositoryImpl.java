package fr.epita.filrouge.infrastructure.movie;

import fr.epita.filrouge.domain.entity.movie.ViewingMovie;
import fr.epita.filrouge.domain.entity.movie.ViewingMovieRepository;
import fr.epita.filrouge.domain.entity.person.AppUser;
import fr.epita.filrouge.domain.exception.ErrorCodes;
import fr.epita.filrouge.domain.exception.NotFoundException;
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
    public void create(ViewingMovie vm) {
        viewingMovieJpaRepository.save(viewingMovieJpaMapper.mapToJpa(vm));
    }

    @Override
    public List<ViewingMovie> findViewingMovieFromUser(AppUser appUser) {
        return viewingMovieJpaMapper.mapToEntity(viewingMovieJpaRepository.findByAppUserJpa(appUserJpaMapper.mapToJpa(appUser)));
    }

    @Override
    public List<ViewingMovie> findViewingMovieFromUserLastname(String lastname) {
        return viewingMovieJpaMapper.mapToEntity(viewingMovieJpaRepository.findByAppUserJpaLastname(lastname));
    }

    @Override
    public List<ViewingMovie> findAllViewingMovie() {
        return viewingMovieJpaMapper.mapToEntity(viewingMovieJpaRepository.findAll());
    }

    @Override
    public void delete(ViewingMovie vm) {
        viewingMovieJpaRepository.delete(viewingMovieJpaMapper.mapToJpa(vm));
    }
}
