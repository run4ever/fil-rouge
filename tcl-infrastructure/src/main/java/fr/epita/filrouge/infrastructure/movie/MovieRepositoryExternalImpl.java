package fr.epita.filrouge.infrastructure.movie;

import fr.epita.filrouge.domain.entity.common.Category;
import fr.epita.filrouge.domain.entity.common.PublicNotation;
import fr.epita.filrouge.domain.entity.movie.Movie;
import fr.epita.filrouge.domain.entity.movie.MovieRepositoryExternal;
import fr.epita.filrouge.domain.exception.ErrorCodes;
import fr.epita.filrouge.domain.exception.ExternalApiTechnicalException;
import fr.epita.filrouge.domain.exception.NotFoundException;
import fr.epita.filrouge.infrastructure.common.PropertiesReader;
import fr.epita.filrouge.infrastructure.http.MovieInfo;
import fr.epita.filrouge.infrastructure.http.MovieSearchInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepositoryExternalImpl implements MovieRepositoryExternal {

    private static final Logger logger = LoggerFactory.getLogger(MovieRepositoryExternalImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Movie searchByApiMovieId(String apiMovieId) {

        try {
            final ResponseEntity<MovieInfo> response = restTemplate.getForEntity("/?i=" + apiMovieId + "&apikey=" + PropertiesReader.getProperty("OMDB_API_KEY"),
                    MovieInfo.class);

            MovieInfo movieInfo = response.getBody();

            logger.debug(movieInfo.toString());

            final Integer virguleIndex = Math.max(0,movieInfo.getCategory().indexOf(","));
            Category movieCategory;
            if(virguleIndex>0){
                movieCategory = Category.valueOf(movieInfo.getCategory().substring(0,virguleIndex).toUpperCase());
            }
            else{
                movieCategory = Category.valueOf(movieInfo.getCategory().toUpperCase());
            }

            final Integer movieDuration;
            if(movieInfo.getDuration().equals("N/A")){
                movieDuration = null;
            }
            else{
                movieDuration = Integer.valueOf(movieInfo.getDuration().replace(" min", ""));
            }

            return Movie.Builder.aMovie()
                    .withImdbId(movieInfo.getImdbID())
                    .withTitle(movieInfo.getTitle())
                    .withDescription(movieInfo.getDescription())
                    .withDuration(movieDuration)
                    .withReleaseDate(LocalDate.of(Integer.valueOf(movieInfo.getYear().substring(0,3)), 1, 1))
                    .withPublicNotation(new PublicNotation(Double.valueOf(movieInfo.getImdbRating()), Integer.valueOf(movieInfo.getImdbVotes().replace(",", ""))))
                    .withActors(movieInfo.getActors())
                    .withImageUrl(movieInfo.getImageUrl())
                    .withCategory(movieCategory)
                    .build();

        }catch (HttpClientErrorException | HttpServerErrorException e) {
        if (HttpStatus.NOT_FOUND.equals(e.getStatusCode())) {
            throw new NotFoundException("Movie " + apiMovieId + " not found in external Api",
                    ErrorCodes.MOVIE_NOT_FOUND);
        }
        throw new ExternalApiTechnicalException("technical exception", e);
    }

    }

    @Override
    public List<Movie> searchByTitle(String title) {

        final ResponseEntity<MovieSearchInfo> response = restTemplate.getForEntity("/?s=" + title + "&type=movie&apikey=" + PropertiesReader.getProperty("OMDB_API_KEY"),
                MovieSearchInfo.class);

        MovieSearchInfo movieSearchInfo = response.getBody();

        final List<Movie> results = new ArrayList<>();

        logger.debug(movieSearchInfo.toString());

        if(movieSearchInfo.getSearch() != null){
            for(MovieInfo m : movieSearchInfo.getSearch()) {
                final String movieId = m.getImdbID();
                final Movie movieToAdd = searchByApiMovieId(movieId);
                results.add(movieToAdd);
            }
        }
        else{
            throw new NotFoundException("No movie match this search",ErrorCodes.MOVIE_NOT_FOUND);
        }
        return results;

    }

}
