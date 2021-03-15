package fr.epita.filrouge.exposition.controller;

import fr.epita.filrouge.application.common.ViewingFrontSearchDto;
import fr.epita.filrouge.application.movie.MovieDto;
import fr.epita.filrouge.application.movie.MovieService;
import fr.epita.filrouge.application.person.AppUserService;
import fr.epita.filrouge.application.viewingmovie.ViewingMovieCreateDto;
import fr.epita.filrouge.application.viewingmovie.ViewingMovieRestitDto;
import fr.epita.filrouge.application.viewingmovie.ViewingMovieService;
import fr.epita.filrouge.domain.entity.common.Status;
import fr.epita.filrouge.exposition.exception.ErrorModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins="http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/viewing-movie")
@Validated
public class ViewingMovieResource {

    @Autowired
    private ViewingMovieService viewingMovieService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private MovieService movieService;

    @GetMapping("/{userEmail}")
    @ApiOperation(value = "récupération de la liste de visonnage de Movie d'un utilisateur", nickname = "getAllViewingMovieOfUser", notes ="Liste de visionnage de movie pour un utilisateur donné")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ErrorModel.class),
            @ApiResponse (code = 404, message = "Not found", response = ErrorModel.class),
            @ApiResponse (code = 500, message = "Internal error", response = ErrorModel.class)
    })
    public ResponseEntity<List<ViewingMovieRestitDto>> getAllViewingMovieOfUser(@PathVariable("userEmail") final String email){
            return new ResponseEntity<> (viewingMovieService.getViewingMovieByUserEmail(email), HttpStatus.OK);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Add a viewing movie in a user viewing list")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success", response = ErrorModel.class),
            @ApiResponse (code = 400, message = "Not found", response = ErrorModel.class),
            @ApiResponse (code = 500, message = "Internal error", response = ErrorModel.class)
    })
    public ResponseEntity<ViewingMovieCreateDto> createViewingMovie(@RequestBody final ViewingMovieCreateDto viewingMovieCreateDto) {
            return new ResponseEntity<> (viewingMovieService.addMovieToViewingMovie(viewingMovieCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @ApiOperation(value = "Update status of a viewing movie")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success", response = ErrorModel.class),
            @ApiResponse (code = 400, message = "Not found", response = ErrorModel.class),
            @ApiResponse (code = 500, message = "Internal error", response = ErrorModel.class)
    })
    public ResponseEntity<ViewingMovieCreateDto> updateViewingMovie(@RequestBody final ViewingMovieCreateDto viewingMovieCreateDto) {
        return new ResponseEntity<> (viewingMovieService.updateViewingMovieStatusOrLike(viewingMovieCreateDto), HttpStatus.CREATED);

    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "Delete a viewing movie")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success", response = ErrorModel.class),
            @ApiResponse (code = 400, message = "Not found", response = ErrorModel.class),
            @ApiResponse (code = 500, message = "Internal error", response = ErrorModel.class)
    })
    public void  deleteViewingMovie(@RequestBody final ViewingMovieCreateDto viewingMovieCreateDto) {
        viewingMovieService.deleteViewingMovie(viewingMovieCreateDto);
    }


    @PostMapping(value = "/search", produces = { "application/json" }, consumes = { "application/json" })
    @ApiOperation(value = "List movies from external API and user viewing list containing searched text in title")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ErrorModel.class),
            @ApiResponse (code = 404, message = "Not found", response = ErrorModel.class),
            @ApiResponse (code = 500, message = "Internal error", response = ErrorModel.class)
    })
    public ResponseEntity<List<ViewingMovieRestitDto>> getMoviesFromApiAndUserViewingList(@RequestBody final ViewingFrontSearchDto viewingFrontSearchDto){

        int pageNum;

        if(viewingFrontSearchDto.getPage() == null){
            pageNum = 1;
        }
        else{
            pageNum = Integer.valueOf(viewingFrontSearchDto.getPage());
        }

        //search title in API
        final List<MovieDto> apiResults = movieService.searchExternalMovie(viewingFrontSearchDto.getTitle(), pageNum);

        //build results list with type of button to display
        List<ViewingMovieRestitDto> searchResults = new ArrayList<>();
        boolean alReadyInUserList;
        boolean like = false;
        Status status;

        for (MovieDto item : apiResults) {
            //if MovieDto is already in user list
            final ViewingMovieRestitDto result = viewingMovieService.verifyViewingMovieExistence(item, viewingFrontSearchDto.getEmail());
            if(result != null){
                alReadyInUserList = true;
                status = result.getStatus();
                like = result.getLove();
            }
            //else
            else{
                alReadyInUserList = false;
                status = Status.TO_WATCH;
            }

            ViewingMovieRestitDto vmToAdd = ViewingMovieRestitDto.Builder.aViewingMovieRestitDto()
                    .withMovieDto(item)
                    .withEmail(viewingFrontSearchDto.getEmail())
                    .withDateLastAction(LocalDate.now())
                    .withStatus(status)
                    .withAlreadyInUserList(alReadyInUserList)
                    .withLove(like)
                    .build();

            searchResults.add(vmToAdd);
        }

        //sort results beginning with element already in list
        Collections.sort(searchResults, (s1, s2) -> Boolean.compare(s2.getAlreadyInUserList(),s1.getAlreadyInUserList()));

        return new ResponseEntity<> (searchResults, HttpStatus.CREATED);

    }

}
