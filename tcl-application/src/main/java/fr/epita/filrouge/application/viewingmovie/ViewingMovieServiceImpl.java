package fr.epita.filrouge.application.viewingmovie;

import fr.epita.filrouge.application.mapper.AppUserDtoMapper;
import fr.epita.filrouge.application.mapper.MovieDtoMapper;
import fr.epita.filrouge.application.mapper.ViewingMovieDtoMapper;
import fr.epita.filrouge.application.movie.MovieDto;
import fr.epita.filrouge.application.person.AppUserDto;
import fr.epita.filrouge.application.viewingmovie.ViewingMovieDto;
import fr.epita.filrouge.application.viewingmovie.ViewingMovieService;
import fr.epita.filrouge.domain.entity.common.Status;
import fr.epita.filrouge.domain.entity.movie.Movie;
import fr.epita.filrouge.domain.entity.movie.ViewingMovie;
import fr.epita.filrouge.domain.entity.movie.ViewingMovieRepository;
import fr.epita.filrouge.domain.entity.person.AppUser;
import fr.epita.filrouge.domain.exception.AlreadyExistingException;
import fr.epita.filrouge.domain.exception.ErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewingMovieServiceImpl implements ViewingMovieService {

    @Autowired
    private ViewingMovieRepository viewingMovieRepository;

    @Autowired
    private ViewingMovieDtoMapper viewingMovieDtoMapper;

    @Autowired
    private AppUserDtoMapper appUserDtoMapper;

    @Autowired
    private MovieDtoMapper movieDtoMapper;

    @Override
    public void addMovieToViewingMovie(AppUserDto appUserDto, MovieDto movieDto) {
        // si un AppUser a déjà le Movie dans son visionnage,
        // alors il n'est pas possible d'ajouter le Movie 2è fois dans ViewingMovie
        AppUser appUser = appUserDtoMapper.mapDtoToDomain(appUserDto);
        Movie movie = movieDtoMapper.mapDtoToDomain(movieDto);

        List<ViewingMovie> viewingMovies = viewingMovieRepository.findViewingMovieFromUser(appUser);

        if(viewingMovies!= null) {//si viewingMovie n'est pas null, il faut tester si on a le Movie déjà dans ViewingMovie

            for (ViewingMovie vm : viewingMovies) {
                if (vm.getMovie().getImdbId() == movieDto.getImdbId()) {
                    throw new AlreadyExistingException("Movie already exiting in ViewingMovie " + movieDto.getImdbId() + " " + movieDto.getTitle(),
                            ErrorCodes.MOVIE_ALREADY_EXISTING_IN_VIEWINGMOVIE);
                }
            }
        }

        //Movie n'est pas encore dans ViewingMovie donc on le crée
        ViewingMovie vm = ViewingMovie.Builder.aViewingMovie()
                .withStatus(Status.TO_WATCH) //à la création on met par défaut Movie à regarder
                .withAppUser(appUser)
                .withMovie(movie)
                .build();
        viewingMovieRepository.create(vm);
    }

    @Override
    public List<ViewingMovieDto> getViewingMovie(AppUserDto appUserDto) {
        if(appUserDto == null) {
            return null;
        }
        AppUser appUser = appUserDtoMapper.mapDtoToDomain(appUserDto);
        return viewingMovieDtoMapper.mapDomainToDto(viewingMovieRepository.findViewingMovieFromUser(appUser));

    }

}
