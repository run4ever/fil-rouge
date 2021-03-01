package fr.epita.filrouge.exposition.controller;

import fr.epita.filrouge.application.common.PageDTO;
import fr.epita.filrouge.application.serie.SearchSerieDto;
import fr.epita.filrouge.application.serie.SerieDto;
import fr.epita.filrouge.application.serie.SerieService;
import fr.epita.filrouge.domain.exception.AlreadyExistingException;
import fr.epita.filrouge.domain.exception.NotFoundException;
import fr.epita.filrouge.infrastructure.exception.TechnicalException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/v1/serie")
@Api(value = "Controller REST pour les series")
public class SerieController {

    private static Logger logger = LoggerFactory.getLogger(SerieController.class);

    @Autowired
    private SerieService iSerieManagement;

    @GetMapping("/{id}")
    @ApiOperation (value = "récupération d'une série", nickname = "getSerie", notes ="création d'une série à partir de son id IMDB")
    @ApiResponses (value = {
            @ApiResponse (code = 200, message = "série trouvée à partir de l'identifiant IMDB", response = SerieDto.class),
            @ApiResponse (code = 404, message = "série absente en base", response = SerieDto.class),
            @ApiResponse (code = 500, message = "Erreur lors de l'accès en base", response = SerieDto.class)
    })
    public ResponseEntity<SerieDto> getSerie(@PathVariable("id") String imdbId) {

        logger.info("imdb Id en entrée : ", imdbId);
        try {
            return new ResponseEntity<SerieDto>(iSerieManagement.getSerieById(imdbId), HttpStatus.OK);
        }
        catch (NotFoundException notFoundException) {
            throw new ResponseStatusException (HttpStatus.NOT_FOUND, notFoundException.getErrorCode ());
        }
        catch (TechnicalException technicalException){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, technicalException.getExceptionMessage ().toString ());
        }
    }

    @PostMapping("/create")
    @ApiOperation (value = "Création unitaire d'une série", nickname = "getSerie", notes ="création d'une série à partir l'IHM")
    @ApiResponses (value = {
            @ApiResponse (code = 201, message = "Série créée avec succès", response = SerieDto.class),
            @ApiResponse (code = 400, message = "La série existe déjà", response = SerieDto.class),
            @ApiResponse (code = 500, message = "Erreur lors de l'accès en base", response = SerieDto.class)
    })
    public ResponseEntity<SerieDto> createSerie(@RequestBody SerieDto serieDTO) {
        try {
            return new ResponseEntity<SerieDto>(iSerieManagement.createSerie (serieDTO), HttpStatus.CREATED);
        }
        catch (AlreadyExistingException alreadyExistingException){
            throw new ResponseStatusException (HttpStatus.BAD_REQUEST, alreadyExistingException.getErrorCode ());
        }
        catch (TechnicalException technicalException){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, technicalException.getExceptionMessage ().toString ());
        }

    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation (value = "Suppression unitaire d'une série", nickname = "getSerie", notes ="création d'une série à partir l'IHM")
    @ApiResponses (value = {
            @ApiResponse (code = 204, message = "Série supprimée avec succès"),
            @ApiResponse (code = 304, message = "Suppression non effectuée"),
            @ApiResponse (code = 500, message = "Erreur lors de l'accès en base")
    })
    public ResponseEntity<Object> deleteSerie (@PathVariable("id") String imdbId) {

        try {
            if (iSerieManagement.deleteSerie (imdbId)) {
                return new ResponseEntity<> (HttpStatus.NO_CONTENT);
            }else {
                throw new ResponseStatusException (HttpStatus.NOT_MODIFIED, "Suppression non effectuée");
            }
        }
        catch (NotFoundException notFoundException) {
            throw new ResponseStatusException (HttpStatus.NOT_FOUND, notFoundException.getErrorCode ());
        }
        catch (TechnicalException technicalException){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, technicalException.getExceptionMessage ().toString ());
        }
    }

    @GetMapping("/list/all")
    @ApiOperation (value = "restitution complète de la liste des séries ", nickname = "getAllSeriesByUser",
            notes ="Resitution de la liste complète des séries selon l'intervalle fourni en entrée")
    @ApiResponses (value = {
            @ApiResponse (code = 200, message = "Restitution de la liste des séries", response = PageDTO.class),
            @ApiResponse (code = 404, message = "Aucune série trouvée", response = PageDTO.class),
            @ApiResponse (code = 500, message = "Erreur lors de l'accès en base", response = PageDTO.class)
    })
    public ResponseEntity<List<SerieDto>> getAllSeries() {
        try {
            return new ResponseEntity<List<SerieDto>>(iSerieManagement.findAllSeries(), HttpStatus.OK);
        }
        catch (NotFoundException notFoundException) {
            throw new ResponseStatusException (HttpStatus.NOT_FOUND, notFoundException.getErrorCode ());
        }
        catch (TechnicalException technicalException){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, technicalException.getExceptionMessage ().toString ());
        }
    }

    @GetMapping("/list/{offset}/{limit}")
    @ApiOperation (value = "restitution paginée de la liste des séries ", nickname = "getAllSeriesByUser",
            notes ="Resitution de la liste complète des séries selon l'intervalle fourni en entrée")
    @ApiResponses (value = {
            @ApiResponse (code = 200, message = "Restitution complète de la liste des séries", response = PageDTO.class),
            @ApiResponse (code = 206, message = "Restitution partielle de la liste des séries", response = PageDTO.class),
            @ApiResponse (code = 404, message = "Aucune série trouvée", response = PageDTO.class),
            @ApiResponse (code = 500, message = "Erreur lors de l'accès en base", response = PageDTO.class)
    })
    public ResponseEntity<PageDTO> getAllSeriesByPage(@PathVariable("offset") int offset, @PathVariable("limit") int limit) {

        if (offset > limit) {
            throw new ResponseStatusException (HttpStatus.BAD_REQUEST, "limit supérieur à offset");
        }
        try {
            PageDTO pageDTO = iSerieManagement.findAllSeriesByPage(offset, limit);
            if (pageDTO.getTotal () <= pageDTO.getLimit () && (pageDTO.getOffset () ==0 )) {
                return new ResponseEntity<PageDTO>(pageDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<PageDTO>(pageDTO, HttpStatus.PARTIAL_CONTENT);
            }

        }
        catch (NotFoundException notFoundException) {
            throw new ResponseStatusException (HttpStatus.NOT_FOUND, notFoundException.getErrorCode ());
        }
        catch (TechnicalException technicalException){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, technicalException.getExceptionMessage ().toString ());
        }
    }

//    @PostMapping("/list/search")
//    @ApiOperation (value = "Recherche d'une série à partir de critères", nickname = "searchSerie",
//            notes ="Recherche multi-critères d'une série existante dans la base")
//    @ApiResponses (value = {
//            @ApiResponse (code = 200, message = "Séries trouvées suite à la recherche, restitution complète", response = PageDTO.class),
//            @ApiResponse (code = 206, message = "Séries trouvées suite à la recherche, restitution partielle", response = PageDTO.class),
//            @ApiResponse (code = 404, message = "Aucune série trouvée", response = PageDTO.class),
//            @ApiResponse (code = 500, message = "Erreur lors de l'accès en base", response = PageDTO.class)
//    })
//    public ResponseEntity<PageDTO> searchSerie(@RequestBody SearchSerieDto searchSerieDto) {
//
//
//        return new ResponseEntity<PageDTO> (iSerieManagement.searchAllSeries(searchSerieDto), HttpStatus.PARTIAL_CONTENT);
//    }
}
