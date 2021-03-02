package fr.epita.filrouge.application.viewingmovie;

import fr.epita.filrouge.application.mapper.AppUserDtoMapper;
import fr.epita.filrouge.application.mapper.MovieDtoMapper;
import fr.epita.filrouge.application.mapper.ViewingMovieDtoMapper;
import fr.epita.filrouge.application.person.AppUserDto;
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
    public ViewingMovieCreateDto addMovieToViewingMovie(ViewingMovieCreateDto viewingMovieCreateDto) {
        // si un AppUser a déjà le Movie dans son visionnage,
        // alors il n'est pas possible d'ajouter le Movie 2è fois dans ViewingMovie
        if (viewingMovieRepository.findViewingMovieFromUserEmailAndMovieId(
                viewingMovieCreateDto.getEmail(), viewingMovieCreateDto.getImdbId()) != null) {
            throw new AlreadyExistingException ("This movie " + viewingMovieCreateDto.getImdbId()
                    + " is already in list of user " + viewingMovieCreateDto.getEmail(), ErrorCodes.MOVIE_ALREADY_EXISTING_IN_VIEWINGMOVIE);
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
    public ViewingMovieCreateDto updateViewingMovieStatus(ViewingMovieCreateDto viewingMovieCreateDto) {

        final ViewingMovie vm = viewingMovieRepository.findViewingMovieFromUserEmailAndMovieId(
                viewingMovieCreateDto.getEmail(), viewingMovieCreateDto.getImdbId());

        vm.setStatus(viewingMovieCreateDto.getStatus());

        viewingMovieRepository.update(vm);

        return null;
    }

    @Override
    public void deleteViewingMovie(ViewingMovieCreateDto viewingMovieCreateDto) {

        final ViewingMovie vm = viewingMovieRepository.findViewingMovieFromUserEmailAndMovieId(
                viewingMovieCreateDto.getEmail(), viewingMovieCreateDto.getImdbId());

        viewingMovieRepository.delete(vm);
    }

}
