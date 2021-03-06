package fr.epita.filrouge.exposition.controller;

import fr.epita.filrouge.application.movie.MovieDto;
import fr.epita.filrouge.application.movie.MovieService;
import fr.epita.filrouge.application.viewingmovie.ViewingMovieService;
import fr.epita.filrouge.application.person.AppUserService;
import fr.epita.filrouge.exposition.exception.ErrorModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins="http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/movie")
@Validated
public class MovieResource {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ViewingMovieService viewingMovieService;

    @Autowired
    private AppUserService appUserService;

    @ApiOperation(value = "Create a new Movie")
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createMovie(@Valid @RequestBody final MovieDto movieDto){
        movieService.createMovieService(movieDto);

        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{imbdId}")
                .buildAndExpand(movieDto.getImdbId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "List All Movies")
    @GetMapping(value = "/list")
    @ResponseStatus(HttpStatus.OK)
    public List<MovieDto> listMovies() {
        return movieService.listAllMoviesService();
    }

    @ApiOperation(value = "Show One Movie details")
    @GetMapping(value = "/{idMovie}", produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public MovieDto showMovieDetails(@PathVariable("idMovie") final String idMovie) {
        return movieService.getOneMovieService(idMovie);
    }

    @GetMapping("/external/show")
    @ResponseStatus(HttpStatus.OK)
    public MovieDto getMovieByExternalId(@RequestParam("externalId") final String apiMovieId) {
        final MovieDto movieDto = movieService.getExternalMovie(apiMovieId);
        return movieDto;
    }

    @GetMapping("/external/addexternal")
    @ResponseStatus(HttpStatus.OK)
    public void createExternalMovie(@RequestParam("externalId") final String apiMovieId) {
        final MovieDto movieDto = movieService.getExternalMovie(apiMovieId);
        movieService.createMovieService(movieDto);
    }

    @GetMapping("/external/search")
    @ApiOperation(value = "Search a movie in Api DB, by its title")
    @ApiResponses(value = {
            @ApiResponse (code = 404, message = "Not found", response = ErrorModel.class),
            @ApiResponse (code = 500, message = "Internal Server Error", response = ErrorModel.class)
    })
    @ResponseStatus(HttpStatus.OK)
    public List<MovieDto> searchExternalMovie(@RequestParam("title") final String title, final Integer pageNum) {
        return movieService.searchExternalMovie(title, pageNum);
    }

    @GetMapping("/external/search-nb-results")
    @ApiOperation(value = "Get results number of a movie search in Api DB, by its title")
    @ApiResponses(value = {
            @ApiResponse (code = 404, message = "Not found", response = ErrorModel.class),
            @ApiResponse (code = 500, message = "Internal Server Error", response = ErrorModel.class)
    })
    @ResponseStatus(HttpStatus.OK)
    public Integer searchExternalMovieNbResults(@RequestParam("title") final String title) {
        return movieService.searchExternalMovieNbResults(title);
    }

    @GetMapping("/loves/{id}")
    @ApiOperation(value = "Get movie nb of likes")
    @ApiResponses(value = {
            @ApiResponse (code = 404, message = "Not found", response = ErrorModel.class),
            @ApiResponse (code = 500, message = "Internal Server Error", response = ErrorModel.class)
    })
    @ResponseStatus(HttpStatus.OK)
    public Integer exposeMovieNbLikes(@PathVariable("id") final String id) {
        return viewingMovieService.searchViewingMovieNbLikes(id);
    }


}
