package fr.epita.filrouge.exposition.api;

import fr.epita.filrouge.application.serie.SerieDto;
import fr.epita.filrouge.application.viewingserie.ViewingSerieCreateDto;
import fr.epita.filrouge.application.viewingserie.ViewingSerieRestitDto;
import fr.epita.filrouge.application.viewingserie.ViewingSerieService;
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
@RequestMapping("v1/viewingserie")
@Api(value = "Controller REST pour les visionnages de série")
public class ViewingSerieController {

    private static Logger logger = LoggerFactory.getLogger(ViewingSerieController.class);

    @Autowired
    private ViewingSerieService viewingSerieService;

    @GetMapping("/{id}")
    @ApiOperation(value = "récupération de la liste de visonnage d'un utilisateur", nickname = "getViewingSerieByUser", notes ="Liste de visionnage d'une série pour un utilisateur donné")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "série trouvée à partir de l'identifiant IMDB", response = SerieDto.class),
            @ApiResponse (code = 400, message = "série absente en base", response = SerieDto.class),
            @ApiResponse (code = 500, message = "Erreur lors de l'accès en base", response = SerieDto.class)
    })
    public ResponseEntity<List<ViewingSerieRestitDto>> getViewingSerieByUser(@PathVariable("id") String email){

        try{
            return new ResponseEntity<List<ViewingSerieRestitDto>> (viewingSerieService.findByUserAllVievingSerieDto (email), HttpStatus.OK);
        }
        catch (NotFoundException notFoundException){
            throw new ResponseStatusException (HttpStatus.BAD_REQUEST, notFoundException.getErrorCode ());
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
    public ResponseEntity<ViewingSerieCreateDto> createSerie(@RequestBody ViewingSerieCreateDto viewingSerieCreateDto) {
        try {
            return new ResponseEntity<ViewingSerieCreateDto> (viewingSerieService.create (viewingSerieCreateDto), HttpStatus.CREATED);
        }
        catch (AlreadyExistingException alreadyExistingException){
            throw new ResponseStatusException (HttpStatus.BAD_REQUEST, alreadyExistingException.getErrorCode ());
        }
        catch (TechnicalException technicalException){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, technicalException.getExceptionMessage ().toString ());
        }
    }

}
