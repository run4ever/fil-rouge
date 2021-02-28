package fr.epita.filrouge.exposition.controller;

import fr.epita.filrouge.application.movie.MovieDto;
import fr.epita.filrouge.application.movie.MovieService;
import fr.epita.filrouge.application.movie.ViewingMovieService;
import fr.epita.filrouge.application.person.AppUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
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
    public void createMovie(@RequestBody final MovieDto movieDto){movieService.createMovieService(movieDto);}

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

    @GetMapping("/external/save")
    @ResponseStatus(HttpStatus.OK)
    public void saveExternalMovie(@RequestParam("externalId") final String apiMovieId) {
        final MovieDto movieDto = movieService.getExternalMovie(apiMovieId);
        movieService.createMovieService(movieDto);
    }

    @GetMapping("/external/search")
    @ResponseStatus(HttpStatus.OK)
    public List<MovieDto> searchExternalMovie(@RequestParam("title") final String title) {
        return movieService.searchExternalMovie(title);
    }


}
