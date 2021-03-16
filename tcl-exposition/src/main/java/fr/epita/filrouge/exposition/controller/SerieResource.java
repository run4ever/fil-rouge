package fr.epita.filrouge.exposition.controller;

import fr.epita.filrouge.application.common.PageDTO;
import fr.epita.filrouge.application.movie.MovieDto;
import fr.epita.filrouge.application.serie.SearchSerieDto;
import fr.epita.filrouge.application.serie.SerieDto;
import fr.epita.filrouge.application.serie.SerieService;
import fr.epita.filrouge.application.viewingserie.ViewingSerieService;
import fr.epita.filrouge.exposition.exception.ErrorModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins="http://localhost:4200", allowedHeaders = "*")
@Validated
@RestController
@RequestMapping("/api/v1/serie")
@Api(value = "Controller REST pour les series")
public class SerieResource {

    private static Logger logger = LoggerFactory.getLogger(SerieResource.class);

    @Autowired
    private SerieService iSerieManagement;

    @Autowired
    private ViewingSerieService viewingSerieService;

    @GetMapping("/{id}")
    @ApiOperation (value = "récupération d'une série", nickname = "getSerie", notes ="création d'une série à partir de son id IMDB")
    @ApiResponses (value = {
            @ApiResponse (code = 200, message = "série trouvée à partir de l'identifiant IMDB", response = ErrorModel.class),
            @ApiResponse (code = 404, message = "série absente en base", response = ErrorModel.class),
            @ApiResponse (code = 500, message = "Erreur lors de l'accès en base", response = ErrorModel.class)
    })
    public ResponseEntity<SerieDto> getSerie(@PathVariable("id") String imdbId) {


        return new ResponseEntity<SerieDto> (iSerieManagement.getSerieById (imdbId), HttpStatus.OK);
    }

    @PostMapping("/create")
    @ApiOperation (value = "Création unitaire d'une série", nickname = "getSerie", notes ="création d'une série à partir l'IHM")
    @ApiResponses (value = {
            @ApiResponse (code = 201, message = "Série créée avec succès", response =  ErrorModel.class),
            @ApiResponse (code = 409, message = "La série existe déjà", response =  ErrorModel.class),
            @ApiResponse (code = 500, message = "Erreur lors de l'accès en base", response =  ErrorModel.class)
    })
    public ResponseEntity<SerieDto> createSerie(@RequestBody SerieDto serieDTO) {

            return new ResponseEntity<SerieDto>(iSerieManagement.createSerie (serieDTO), HttpStatus.CREATED);

    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation (value = "Suppression unitaire d'une série", nickname = "getSerie", notes ="création d'une série à partir l'IHM")
    @ApiResponses (value = {
            @ApiResponse (code = 204, message = "Série supprimée avec succès",response =  ErrorModel.class),
            @ApiResponse (code = 304, message = "Suppression non effectuée",response =  ErrorModel.class),
            @ApiResponse (code = 500, message = "Erreur lors de l'accès en base",response =  ErrorModel.class)
    })
    public ResponseEntity<Object> deleteSerie (@PathVariable("id") String imdbId) {


        if (iSerieManagement.deleteSerie (imdbId)) {
            return new ResponseEntity<> (HttpStatus.NO_CONTENT);
        } else {
            throw new ResponseStatusException (HttpStatus.NOT_MODIFIED, "Suppression non effectuée");
        }
    }

    @GetMapping("/list/all")
    @ApiOperation (value = "restitution complète de la liste des séries ", nickname = "getAllSeriesByUser",
            notes ="Resitution de la liste complète des séries selon l'intervalle fourni en entrée")
    @ApiResponses (value = {
            @ApiResponse (code = 200, message = "Restitution de la liste des séries", response = ErrorModel.class),
            @ApiResponse (code = 404, message = "Aucune série trouvée", response = ErrorModel.class),
            @ApiResponse (code = 500, message = "Erreur lors de l'accès en base", response = ErrorModel.class)
    })
    public ResponseEntity<List<SerieDto>> getAllSeries() {

            return new ResponseEntity<List<SerieDto>>(iSerieManagement.findAllSeries(), HttpStatus.OK);

   }

    @PostMapping("/list/search")
    @ApiOperation (value = "Recherche d'une série à partir de critères", nickname = "searchSerie",
            notes ="Recherche multi-critères d'une série existante dans la base")
    @ApiResponses (value = {
            @ApiResponse (code = 200, message = "Séries trouvées suite à la recherche, restitution complète", response = ErrorModel.class),
            @ApiResponse (code = 206, message = "Séries trouvées suite à la recherche, restitution partielle", response = ErrorModel.class),
            @ApiResponse (code = 404, message = "Aucune série trouvée", response = ErrorModel.class),
            @ApiResponse (code = 500, message = "Erreur lors de l'accès en base", response = ErrorModel.class)
    })
    public ResponseEntity<PageDTO> searchSerie(@RequestBody SearchSerieDto searchSerieDto) {
        return new ResponseEntity<PageDTO> (iSerieManagement.searchAllSeries(searchSerieDto), HttpStatus.PARTIAL_CONTENT);
    }

    @GetMapping("/external/show")
    @ResponseStatus(HttpStatus.OK)
    public SerieDto getSerieByExternalId(@RequestParam("externalId") final String apiSerieId) {
        final SerieDto serieDto = iSerieManagement.getExternalSerie(apiSerieId);
        return serieDto;
    }

    @GetMapping("/external/addexternal")
    @ResponseStatus(HttpStatus.OK)
    public void createExternalSerie(@RequestParam("externalId") final String apiSerieId) {
        final SerieDto serieDto = iSerieManagement.getExternalSerie(apiSerieId);
        iSerieManagement.createSerie(serieDto);
    }

    @GetMapping("/external/search")
    @ApiOperation(value = "Search a serie in Api DB, by its title")
    @ApiResponses(value = {
            @ApiResponse (code = 404, message = "Not found", response = ErrorModel.class),
            @ApiResponse (code = 500, message = "Internal Server Error", response = ErrorModel.class)
    })
    @ResponseStatus(HttpStatus.OK)
    public List<SerieDto> searchExternalSerie(@RequestParam("title") final String title, final Integer pageNum) {
        return iSerieManagement.searchExternalSerie(title, pageNum);
    }

    @GetMapping("/external/search-nb-results")
    @ApiOperation(value = "Get results number of a serie search in Api DB, by its title")
    @ApiResponses(value = {
            @ApiResponse (code = 404, message = "Not found", response = ErrorModel.class),
            @ApiResponse (code = 500, message = "Internal Server Error", response = ErrorModel.class)
    })
    @ResponseStatus(HttpStatus.OK)
    public Integer searchExternalSerieNbResults(@RequestParam("title") final String title) {
        return iSerieManagement.searchExternalSerieNbResults(title);
    }

    @GetMapping("/loves/{id}")
    @ApiOperation(value = "Get serie nb of likes")
    @ApiResponses(value = {
            @ApiResponse (code = 404, message = "Not found", response = ErrorModel.class),
            @ApiResponse (code = 500, message = "Internal Server Error", response = ErrorModel.class)
    })
    @ResponseStatus(HttpStatus.OK)
    public Integer exposeSerieNbLikes(@PathVariable("id") final String id) {
        return viewingSerieService.searchViewingSerieNbLikes(id);
    }

}
