package fr.epita.filrouge.exposition.controller;

import fr.epita.filrouge.application.common.ViewingFrontSearchDto;
import fr.epita.filrouge.application.serie.SerieDto;
import fr.epita.filrouge.application.serie.SerieService;
import fr.epita.filrouge.application.viewingserie.ViewingSerieCreateDto;
import fr.epita.filrouge.application.viewingserie.ViewingSerieRestitDto;
import fr.epita.filrouge.application.viewingserie.ViewingSerieService;
import fr.epita.filrouge.domain.entity.common.Status;
import fr.epita.filrouge.exposition.exception.ErrorModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins="http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/viewing-serie")
@Api(value = "Controller REST pour les visionnages de série")
public class ViewingSerieResource {

    @Autowired
    private ViewingSerieService viewingSerieService;

    @Autowired
    private SerieService serieService;

    @GetMapping("/{userEmail}")
    @ApiOperation(value = "récupération de la liste de visonnage de Série d'un utilisateur", nickname = "getViewingSerieByUser", notes ="Liste de visionnage d'une série pour un utilisateur donné")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Visionnage trouvé à partir de l'identifiant user", response = ErrorModel.class),
            @ApiResponse (code = 400, message = "Visionnage absent en base", response = SerieDto.class),
            @ApiResponse (code = 500, message = "Erreur lors de l'accès en base", response = SerieDto.class)
    })
    public ResponseEntity<List<ViewingSerieRestitDto>> getViewingSerieByUser(@PathVariable("userEmail") String email){


            return new ResponseEntity<> (viewingSerieService.findByUserAllVievingSerieDto (email), HttpStatus.OK);

    }

    @PostMapping("/create")
    @ApiOperation (value = "Création unitaire d'un visionnage", nickname = "getSerie", notes ="création d'une série à partir l'IHM")
    @ApiResponses (value = {
            @ApiResponse (code = 201, message = "visionnage créé avec succès", response = ErrorModel.class),
            @ApiResponse (code = 400, message = "Visionnage existe déjà", response = ErrorModel.class),
            @ApiResponse (code = 500, message = "Erreur lors de l'accès en base", response = ErrorModel.class)
    })
    public ResponseEntity<ViewingSerieCreateDto> createSerie(@RequestBody ViewingSerieCreateDto viewingSerieCreateDto) {

            return new ResponseEntity<> (viewingSerieService.create (viewingSerieCreateDto), HttpStatus.CREATED);
        }

    @PutMapping("/update")
    @ApiOperation(value = "Update status of a viewing serie")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success", response = ErrorModel.class),
            @ApiResponse (code = 400, message = "Not found", response = ErrorModel.class),
            @ApiResponse (code = 500, message = "Internal error", response = ErrorModel.class)
    })
    public ResponseEntity<ViewingSerieCreateDto> updateViewingSerie(@RequestBody final ViewingSerieCreateDto viewingSerieCreateDto) {
        return new ResponseEntity<> (viewingSerieService.updateViewingSerieStatus(viewingSerieCreateDto), HttpStatus.CREATED);

    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "Delete a viewing serie")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success", response = ErrorModel.class),
            @ApiResponse (code = 400, message = "Not found", response = ErrorModel.class),
            @ApiResponse (code = 500, message = "Internal error", response = ErrorModel.class)
    })
    public void  deleteViewingSerie(@RequestBody final ViewingSerieCreateDto viewingSerieCreateDto) {
         viewingSerieService.deleteViewingSerie (viewingSerieCreateDto);
    }

    @PostMapping(value = "/search", produces = { "application/json" }, consumes = { "application/json" })
    @ApiOperation(value = "List series from external API and user viewing list containing searched text in title")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ErrorModel.class),
            @ApiResponse (code = 404, message = "Not found", response = ErrorModel.class),
            @ApiResponse (code = 500, message = "Internal error", response = ErrorModel.class)
    })
    public ResponseEntity<List<ViewingSerieRestitDto>> getSeriesFromApiAndUserViewingList(@RequestBody final ViewingFrontSearchDto viewingFrontSearchDto){

        int pageNum;

        if(viewingFrontSearchDto.getPage() == null){
            pageNum = 1;
        }
        else{
            pageNum = Integer.valueOf(viewingFrontSearchDto.getPage());
        }

        //search title in API
        final List<SerieDto> apiResults = serieService.searchExternalSerie(viewingFrontSearchDto.getTitle(), pageNum);

        //build results list with type of button to display
        List<ViewingSerieRestitDto> searchResults = new ArrayList<>();
        boolean alReadyInUserList;
        Status status;

        for (SerieDto item : apiResults) {
            //if SerieDto is already in user list
            final ViewingSerieRestitDto result = viewingSerieService.verifyViewingSerieExistence(item, viewingFrontSearchDto.getEmail());
            if(result != null){
                alReadyInUserList = true;
                status = result.getStatus();
            }
            //else
            else{
                alReadyInUserList = false;
                status = Status.TO_WATCH;
            }

            ViewingSerieRestitDto vsToAdd = ViewingSerieRestitDto.Builder.aViewingSerieRestitDto()
                    .withSerieDto(item)
                    .withEmail(viewingFrontSearchDto.getEmail())
                    .withDateLastAction(LocalDate.now())
                    .withStatus(status)
                    .withAlreadyInUserList(alReadyInUserList)
                    .build();

            searchResults.add(vsToAdd);
        }

        //sort results beginning with element already in list
        Collections.sort(searchResults, (s1, s2) -> Boolean.compare(s2.getAlreadyInUserList(),s1.getAlreadyInUserList()));

        return new ResponseEntity<> (searchResults, HttpStatus.CREATED);

    }


}
