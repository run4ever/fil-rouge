package fr.epita.filrouge.infrastructure.serie;

import fr.epita.filrouge.domain.entity.common.Category;
import fr.epita.filrouge.domain.entity.common.PublicNotation;
import fr.epita.filrouge.domain.entity.serie.Serie;
import fr.epita.filrouge.domain.entity.serie.SerieRepositoryExternal;
import fr.epita.filrouge.domain.exception.ErrorCodes;
import fr.epita.filrouge.domain.exception.ExternalApiTechnicalException;
import fr.epita.filrouge.domain.exception.NotFoundException;
import fr.epita.filrouge.infrastructure.http.SerieInfo;
import fr.epita.filrouge.infrastructure.http.SerieSearchInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SerieRepositoryExternalImpl implements SerieRepositoryExternal {

    private static final Logger logger = LoggerFactory.getLogger(SerieRepositoryExternalImpl.class);

    @Value("${OMDB_API_KEY}")
    private String omdbApiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Serie searchByApiSerieId(String apiSerieId) {

        try {
            final ResponseEntity<SerieInfo> response = restTemplate.getForEntity("/?i=" + apiSerieId + "&apikey=" + omdbApiKey,
                    SerieInfo.class);

            SerieInfo serieInfo = response.getBody();

            logger.debug(serieInfo.toString());

            final Integer virguleIndex = Math.max(0,serieInfo.getCategory().indexOf(","));
            Category serieCategory;
            if(virguleIndex>0){
                serieCategory = Category.valueOf(serieInfo.getCategory().substring(0,virguleIndex).toUpperCase());
            }
            else{
                if(serieInfo.getCategory().equals("N/A")){
                    serieCategory = Category.NONE;
                }
                else{
                    serieCategory = Category.valueOf(serieInfo.getCategory().replace("-","_").toUpperCase());
                }
            }

            Integer totalSeasons = null;
            if(serieInfo.getTotalSeasons().equals("N/A")){
                totalSeasons = null;
            }
            else{
                totalSeasons = Integer.valueOf(serieInfo.getTotalSeasons());
            }

            Integer endYear = null;
            Integer startYear = null;
            if(serieInfo.getYear().equals("N/A")){
                startYear=null;
                endYear = null;
            }
            else{
                startYear =  Integer.valueOf(serieInfo.getYear().substring(0,4));
                if(serieInfo.getYear().length() > 6){
                    endYear = Integer.valueOf(serieInfo.getYear().substring(5,9));
                }
            }

            Double notation;
            if(serieInfo.getImdbRating().equals("N/A")){
                notation = null;
            }
            else{
                notation = Double.valueOf(serieInfo.getImdbRating());
            }

            Integer votes;
            if(serieInfo.getImdbVotes().equals("N/A")){
                votes = null;
            }
            else{
                votes = Integer.valueOf(serieInfo.getImdbVotes().replace(",", ""));
            }

            return Serie.Builder.aSerie()
                    .withImdbId(serieInfo.getImdbID())
                    .withTitle(serieInfo.getTitle())
                    .withDescription(serieInfo.getDescription())
                    .withNumberOfSeason(totalSeasons)
                    .withStartYear(startYear)
                    .withEndYear(endYear)
                    .withPublicNotation(new PublicNotation(notation, votes))
                    .withActors(serieInfo.getActors())
                    .withImageUrl(serieInfo.getImageUrl())
                    .withCategory(serieCategory)
                    .build();

        }catch (HttpClientErrorException | HttpServerErrorException e) {
            if (HttpStatus.NOT_FOUND.equals(e.getStatusCode())) {
                throw new NotFoundException("Serie " + apiSerieId + " not found in external Api",
                        ErrorCodes.SERIE_NOT_FOUND);
            }
            throw new ExternalApiTechnicalException("technical exception", e);
        }
    }

    @Override
    public List<Serie> searchByTitle(String title) {
        final ResponseEntity<SerieSearchInfo> response = restTemplate.getForEntity("/?s=" + title + "&type=series&apikey=" + omdbApiKey,
                SerieSearchInfo.class);

        SerieSearchInfo serieSearchInfo = response.getBody();

        final List<Serie> results = new ArrayList<>();

        logger.debug(serieSearchInfo.toString());

        if(serieSearchInfo.getSearch() != null){
            for(SerieInfo m : serieSearchInfo.getSearch()) {
                final String serieId = m.getImdbID();
                final Serie serieToAdd = searchByApiSerieId(serieId);
                results.add(serieToAdd);
            }
        }
        else{
            throw new NotFoundException("No serie match this search",ErrorCodes.SERIE_NOT_FOUND);
        }
        return results;
    }

}
