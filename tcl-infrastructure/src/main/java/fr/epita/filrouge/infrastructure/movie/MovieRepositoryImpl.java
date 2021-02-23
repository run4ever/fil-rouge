package fr.epita.filrouge.infrastructure.movie;

import fr.epita.filrouge.domain.entity.movie.Movie;
import fr.epita.filrouge.domain.entity.movie.MovieRepository;
import fr.epita.filrouge.domain.exception.ErrorCodes;
import fr.epita.filrouge.domain.exception.NotFoundException;
import fr.epita.filrouge.infrastructure.mapper.MovieJpaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public Movie findOne(Long id) {
        return movieJpaMapper.mapToEntity(movieJpaRepository.findById(id).orElseThrow(() -> new NotFoundException("No Movie found with ID "+id, ErrorCodes.MOVIE_NOT_FOUND)));
    }

    @Override
    public Movie findOneFromTitle(String movieTitle) {
        return movieJpaMapper.mapToEntity(movieJpaRepository.findByTitle(movieTitle));
    }
}
