package fr.epita.filrouge.infrastructure.movie;

import fr.epita.filrouge.domain.entity.common.Category;
import fr.epita.filrouge.domain.entity.common.PublicNotation;
import fr.epita.filrouge.domain.entity.movie.Movie;
import fr.epita.filrouge.domain.entity.movie.MovieRepositoryExternal;
import fr.epita.filrouge.domain.exception.ErrorCodes;
import fr.epita.filrouge.domain.exception.ExternalApiTechnicalException;
import fr.epita.filrouge.domain.exception.NotFoundException;
import fr.epita.filrouge.infrastructure.http.MovieInfo;
import fr.epita.filrouge.infrastructure.http.MovieSearchInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${OMDB_API_KEY}")
    private String omdbApiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Movie searchByApiMovieId(String apiMovieId) {

        try {
            final ResponseEntity<MovieInfo> response = restTemplate.getForEntity("/?i=" + apiMovieId + "&apikey=" + omdbApiKey,
                    MovieInfo.class);

            MovieInfo movieInfo = response.getBody();

            logger.debug(movieInfo.toString());

            final Integer virguleIndex = Math.max(0,movieInfo.getCategory().indexOf(","));
            Category movieCategory;
            String catLabel = movieInfo.getCategory().toUpperCase();

            if(catLabel.equals("N/A")){
                movieCategory = Category.NONE;
            }
            else{
                if(virguleIndex>0){
                    catLabel = catLabel.substring(0,virguleIndex);
                }
                movieCategory = Category.valueOf(catLabel.replace(' ','_').replace('-','_'));
            }

            Integer movieDuration;
            if(movieInfo.getDuration().equals("N/A")){
                movieDuration = null;
            }
            else{
                movieDuration = Integer.valueOf(movieInfo.getDuration().replace(" min", ""));
            }

            LocalDate date;
            if(movieInfo.getYear().equals("N/A")){
                date = null;
            }
            else{
                date = LocalDate.of(Integer.valueOf(movieInfo.getYear().substring(0,4)), 1, 1);
            }

            Double notation;
            if(movieInfo.getImdbRating().equals("N/A")){
                notation = null;
            }
            else{
                notation = Double.valueOf(movieInfo.getImdbRating());
            }

            Integer votes;
            if(movieInfo.getImdbVotes().equals("N/A")){
                votes = null;
            }
            else{
                votes = Integer.valueOf(movieInfo.getImdbVotes().replace(",", ""));
            }

            return Movie.Builder.aMovie()
                    .withImdbId(movieInfo.getImdbID())
                    .withTitle(movieInfo.getTitle())
                    .withDescription(movieInfo.getDescription())
                    .withDuration(movieDuration)
                    .withReleaseDate(date)
                    .withPublicNotation(new PublicNotation(notation, votes))
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
    public Integer getApiSearchNbResults(String title) {
        final ResponseEntity<MovieSearchInfo> response = restTemplate.getForEntity("/?s=" + title + "&type=movie&apikey=" + omdbApiKey,
                MovieSearchInfo.class);

        MovieSearchInfo movieSearchInfo = response.getBody();

        if(movieSearchInfo.getSearch() == null){
            return 0;
        }

        return Integer.valueOf(movieSearchInfo.getTotalResults());
    }

    @Override
    public List<Movie> searchByTitle(String title, Integer pageNum) {

        final ResponseEntity<MovieSearchInfo> firstResponse = restTemplate.getForEntity("/?s=" + title + "&type=movie&apikey=" + omdbApiKey,
                MovieSearchInfo.class);

        MovieSearchInfo movieFirstSearchInfo = firstResponse.getBody();

        int nbApiResultsPages;
        if(movieFirstSearchInfo.getTotalResults() != null){
            nbApiResultsPages = (int) Math.ceil((double) Integer.valueOf(movieFirstSearchInfo.getTotalResults()) / 10);
        }
        else{
            nbApiResultsPages=0;
        }

        final List<Movie> results = new ArrayList<>();

        final ResponseEntity<MovieSearchInfo> response = restTemplate.getForEntity("/?s=" + title + "&type=movie&page=" + pageNum + "&apikey=" + omdbApiKey,
                MovieSearchInfo.class);

        MovieSearchInfo movieSearchInfo = response.getBody();

        if(movieFirstSearchInfo.getSearch() == null || movieSearchInfo.getSearch() == null){
            throw new NotFoundException("No movie match this search",ErrorCodes.MOVIE_NOT_FOUND);
        }

        for(MovieInfo m : movieSearchInfo.getSearch()) {
            final String movieId = m.getImdbID();
            final Movie movieToAdd = searchByApiMovieId(movieId);
            results.add(movieToAdd);
        }

        return results;

    }

}
