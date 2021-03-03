package fr.epita.filrouge.exposition.controller;

import fr.epita.filrouge.application.movie.MovieService;
import fr.epita.filrouge.application.person.AppUserService;
import fr.epita.filrouge.application.viewingmovie.ViewingMovieCreateDto;
import fr.epita.filrouge.application.viewingmovie.ViewingMovieRestitDto;
import fr.epita.filrouge.application.viewingmovie.ViewingMovieService;
import fr.epita.filrouge.domain.exception.AlreadyExistingException;
import fr.epita.filrouge.domain.exception.NotFoundException;
import fr.epita.filrouge.exposition.exception.ErrorModel;
import fr.epita.filrouge.infrastructure.exception.TechnicalException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
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

    @PostMapping("/list")
    @ApiOperation(value = "List viewing movies of user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ErrorModel.class),
            @ApiResponse (code = 404, message = "Not found", response = ErrorModel.class),
            @ApiResponse (code = 500, message = "Internal error", response = ErrorModel.class)
    })
    public ResponseEntity<List<ViewingMovieRestitDto>> getAllViewingMovieOfUser(@RequestBody final String email){
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
        return new ResponseEntity<> (viewingMovieService.updateViewingMovieStatus(viewingMovieCreateDto), HttpStatus.CREATED);

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


}
