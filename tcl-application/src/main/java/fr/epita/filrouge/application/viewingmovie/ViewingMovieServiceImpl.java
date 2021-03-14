package fr.epita.filrouge.application.viewingmovie;

import fr.epita.filrouge.application.mapper.AppUserDtoMapper;
import fr.epita.filrouge.application.mapper.MovieDtoMapper;
import fr.epita.filrouge.application.mapper.ViewingMovieDtoMapper;
import fr.epita.filrouge.application.movie.MovieDto;
import fr.epita.filrouge.application.movie.MovieService;
import fr.epita.filrouge.application.person.AppUserDto;
import fr.epita.filrouge.domain.entity.viewingmovie.ViewingMovie;
import fr.epita.filrouge.domain.entity.viewingmovie.ViewingMovieRepository;
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

    @Autowired
    private MovieService movieService;

    @Override
    public ViewingMovieCreateDto addMovieToViewingMovie(ViewingMovieCreateDto viewingMovieCreateDto) {
        // si un AppUser a déjà le Movie dans son visionnage,
        // alors il n'est pas possible d'ajouter le Movie 2è fois dans ViewingMovie
        if (viewingMovieRepository.findViewingMovieFromUserEmailAndMovieId(
                viewingMovieCreateDto.getEmail(), viewingMovieCreateDto.getImdbId()) != null) {
            throw new AlreadyExistingException ("This movie " + viewingMovieCreateDto.getImdbId()
                    + " is already in list of user " + viewingMovieCreateDto.getEmail(), ErrorCodes.MOVIE_ALREADY_EXISTING_IN_VIEWINGMOVIE);
        }
        //vérifier si Movie est présent ou pas dans la table Movie si absent alors il faut ajouter Movie avant create viewingMovie
        MovieDto movieDto = movieService.getOneMovieService(viewingMovieCreateDto.getImdbId());
        if(movieDto == null) {
            //movie est absent de la table Movie, donc appel API externe pour avoir les infos puis le crée dans la table Movie
            movieService.createMovieService(movieService.getExternalMovie(viewingMovieCreateDto.getImdbId()));
        }

        return viewingMovieDtoMapper.mapDomainToCreateDto(viewingMovieRepository.create(viewingMovieDtoMapper.mapCreateDtoToDomain(viewingMovieCreateDto)));

    }

    @Override
    public List<ViewingMovieRestitDto> getViewingMovie(AppUserDto appUserDto) {
        if(appUserDto == null) {
            return null;
        }
        AppUser appUser = appUserDtoMapper.mapDtoToDomain(appUserDto);
        return viewingMovieDtoMapper.mapDomainToDto(viewingMovieRepository.findViewingMovieFromUser(appUser));

    }

    @Override
    public List<ViewingMovieRestitDto> getViewingMovieByUserEmail(String email) {
        if(email == null) {
            return null;
        }

        return viewingMovieDtoMapper.mapDomainToDto(viewingMovieRepository.findViewingMovieFromUserEmail(email));
    }

    @Override
    public ViewingMovieCreateDto updateViewingMovieStatusOrLike(ViewingMovieCreateDto viewingMovieCreateDto) {

        final ViewingMovie vm = viewingMovieRepository.findViewingMovieFromUserEmailAndMovieId(
                viewingMovieCreateDto.getEmail(), viewingMovieCreateDto.getImdbId());

        vm.setStatus(viewingMovieCreateDto.getStatus());
        vm.setLove(viewingMovieCreateDto.getLove());

        viewingMovieRepository.update(vm);

        return null;
    }

    @Override
    public void deleteViewingMovie(ViewingMovieCreateDto viewingMovieCreateDto) {

        final ViewingMovie vm = viewingMovieRepository.findViewingMovieFromUserEmailAndMovieId(
                viewingMovieCreateDto.getEmail(), viewingMovieCreateDto.getImdbId());

        viewingMovieRepository.delete(vm);
    }

    @Override
    public ViewingMovieRestitDto verifyViewingMovieExistence(MovieDto movie, String email) {
        return viewingMovieDtoMapper.mapDomainToDto(viewingMovieRepository.findViewingMovieFromUserEmailAndMovieId(email, movie.getImdbId()));
    }

    @Override
    public Integer searchViewingMovieNbLikes(String idMovie) {
        return viewingMovieRepository.countViewingMovieLikesByMovieid(idMovie);
    }

}
