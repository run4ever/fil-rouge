package fr.epita.filrouge.application.movie;

import fr.epita.filrouge.domain.entity.movie.Movie;
import fr.epita.filrouge.domain.entity.movie.MovieRepository;
import fr.epita.filrouge.domain.exception.AlreadyExistingException;
import fr.epita.filrouge.domain.exception.ErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;

public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void createMovieService(Movie movie) {
            final Movie searchResult = movieRepository.findMovieFromApiId(movie.getImdbId());
            if(searchResult != null) {
                throw new AlreadyExistingException("Movie already existing " + movie.getTitle(), ErrorCodes.MOVIE_ALREADY_EXISTING);
            }
            else{
                    movieRepository.create(movie);
                }
    }

    @Override
    public Movie getOneMovieService(String apiMovieId) {
        if(apiMovieId == null) {
            return null;
        }
        return movieRepository.findMovieFromApiId(apiMovieId);
    }
}
