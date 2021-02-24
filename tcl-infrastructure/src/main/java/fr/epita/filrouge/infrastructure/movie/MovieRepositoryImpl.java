package fr.epita.filrouge.infrastructure.movie;

import fr.epita.filrouge.domain.entity.movie.Movie;
import fr.epita.filrouge.domain.entity.movie.MovieRepository;
import fr.epita.filrouge.domain.exception.ErrorCodes;
import fr.epita.filrouge.domain.exception.NotFoundException;
import fr.epita.filrouge.infrastructure.mapper.MovieJpaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    @Autowired
    private MovieJpaRepository movieJpaRepository;

    @Autowired
    private MovieJpaMapper movieJpaMapper;

    @Override
    public void create(Movie m) {
        movieJpaRepository.save(movieJpaMapper.mapToJpa(m));
    }

    @Override
    public Movie findMovieFromApiId(String id) {
        return movieJpaMapper.mapToEntity(movieJpaRepository.findByImdbId(id));
    }

    @Override
    public List<Movie> findMovieFromTitle(String movieTitle) {
        return movieJpaMapper.mapToEntity(movieJpaRepository.findByTitle(movieTitle));
    }
}
