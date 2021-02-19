package fr.epita.filrouge.infrastructure.movie;

import fr.epita.filrouge.domain.entity.movie.ViewingMovie;
import fr.epita.filrouge.infrastructure.person.AppUserJpaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ViewingMovieJpaMapper {

    @Autowired
    private AppUserJpaMapper appUserJpaMapper;

    @Autowired
    private MovieJpaMapper movieJpaMapper;

    public ViewingMovieJpa mapToJpa(ViewingMovie entity){
        if (entity == null) {
            return null;
        }
        return ViewingMovieJpa.Builder.aViewingMovieJpa()
                .withId(entity.getId())
                .withStatus(entity.getStatus())
                .withAppUserJpa(appUserJpaMapper.mapToJpa(entity.getAppUser()))
                .withMovieJpa(movieJpaMapper.mapToJpa(entity.getMovie()))
                .build();
    }

    public List<ViewingMovieJpa> mapToJpa(List<ViewingMovie> entities){
        if (entities == null) {
            return null;
        }
        final List<ViewingMovieJpa> viewingMoviesJpa = new ArrayList<ViewingMovieJpa>();
        for( ViewingMovie item : entities ) {
            viewingMoviesJpa.add(mapToJpa(item));
        }
        return viewingMoviesJpa;
    }

    public ViewingMovie mapToEntity(ViewingMovieJpa jpa){
        if (jpa == null) {
            return null;
        }

        final ViewingMovie entity = ViewingMovie.Builder.aViewingMovie()
                .withId(jpa.getId())
                .withStatus(jpa.getStatus())
                .withAppUser(appUserJpaMapper.mapToEntity(jpa.getAppUserJpa()))
                .withMovie(movieJpaMapper.mapToEntity(jpa.getMovieJpa()))
                .build();

        return entity;
    }

    public List<ViewingMovie> mapToEntity(List<ViewingMovieJpa> jpas){
        if (jpas == null) {
            return null;
        }
        final List<ViewingMovie> viewingMovies = new ArrayList<ViewingMovie>();
        for( ViewingMovieJpa item : jpas ) {
            viewingMovies.add(mapToEntity(item));
        }
        return viewingMovies;
    }

}
