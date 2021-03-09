package fr.epita.filrouge.application.movie;

import fr.epita.filrouge.application.mapper.MovieDtoMapper;
import fr.epita.filrouge.domain.entity.movie.Movie;
import fr.epita.filrouge.domain.entity.movie.MovieRepository;
import fr.epita.filrouge.domain.entity.movie.MovieRepositoryExternal;
import fr.epita.filrouge.domain.exception.AlreadyExistingException;
import fr.epita.filrouge.domain.exception.ErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieRepositoryExternal movieRepositoryExternal;

    @Autowired
    private MovieDtoMapper movieDtoMapper;

    @Override
    public void createMovieService(MovieDto movieDto) {
            final Movie searchResult = movieRepository.findMovieFromApiId(movieDto.getImdbId());
            if(searchResult != null) {
                throw new AlreadyExistingException("Movie already existing " + movieDto.getTitle(), ErrorCodes.MOVIE_ALREADY_EXISTING);
            }
            else{
                    movieRepository.create(movieDtoMapper.mapDtoToDomain(movieDto));
                }
    }

    @Override
    public MovieDto getOneMovieService(String apiMovieId) {
        if(apiMovieId == null) {
            return null;
        }
        return movieDtoMapper.mapDomainToDto(movieRepository.findMovieFromApiId(apiMovieId));
    }

    @Override
    public List<MovieDto> listAllMoviesService() {
        return  movieDtoMapper.mapDomainToDto(movieRepository.findAllMovies());
    }

    @Override
    public Integer searchExternalMovieNbResults(String title) {
        return movieRepositoryExternal.getApiSearchNbResults(title);
    }

    @Override
    public List<MovieDto> searchExternalMovie(String title, Integer pageNum) {
        return movieDtoMapper.mapDomainToDto(movieRepositoryExternal.searchByTitle(title, pageNum));
    }

    @Override
    public MovieDto getExternalMovie(String apiMovieId) {
        return movieDtoMapper.mapDomainToDto(movieRepositoryExternal.searchByApiMovieId(apiMovieId));
    }

}
