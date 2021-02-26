package fr.epita.filrouge.application.movie;

import fr.epita.filrouge.application.mapper.MovieDtoMapper;
import fr.epita.filrouge.domain.entity.movie.Movie;
import fr.epita.filrouge.domain.entity.movie.MovieRepository;
import fr.epita.filrouge.domain.exception.AlreadyExistingException;
import fr.epita.filrouge.domain.exception.ErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieDtoMapper movieDtoMapper;

    @Override
    public void createMovieService(MovieDto movieDto) {
            final Movie searchResult = movieRepository.findMovieFromApiId(movieDto.getImdbId());
            if(searchResult != null) {
                throw new AlreadyExistingException("Movie already existing " + movieDto.getTitle(), ErrorCodes.MOVIE_ALREADY_EXISTING);
            }
            else{
                    movieRepository.create(movieDtoMapper.mapToEntity(movieDto));
                }
    }

    @Override
    public MovieDto getOneMovieService(String apiMovieId) {
        if(apiMovieId == null) {
            return null;
        }
        return movieDtoMapper.mapToDto(movieRepository.findMovieFromApiId(apiMovieId));
    }

    @Override
    public List<MovieDto> listAllMovies() {
        return movieDtoMapper.mapToDto(movieRepository.findAllMovies());
    }
}
