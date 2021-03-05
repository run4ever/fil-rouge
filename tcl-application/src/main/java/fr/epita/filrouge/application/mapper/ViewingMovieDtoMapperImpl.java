package fr.epita.filrouge.application.mapper;

import fr.epita.filrouge.application.viewingmovie.ViewingMovieCreateDto;
import fr.epita.filrouge.application.viewingmovie.ViewingMovieRestitDto;
import fr.epita.filrouge.domain.entity.movie.MovieRepository;
import fr.epita.filrouge.domain.entity.movie.ViewingMovie;
import fr.epita.filrouge.domain.entity.person.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ViewingMovieDtoMapperImpl implements ViewingMovieDtoMapper {

    @Autowired
    private MovieDtoMapper movieDtoMapper;

    @Autowired
    private AppUserDtoMapper appUserDtoMapper;

    @Autowired
    private AppUserRepository appUserService;

    @Autowired
    private MovieRepository movieService;

    @Override
    public ViewingMovie mapDtoToDomain(ViewingMovieRestitDto viewingMovieRestitDto) {
        if ( viewingMovieRestitDto == null ) {
            return null;
        }

        return ViewingMovie.Builder.aViewingMovie()
                .withStatus(viewingMovieRestitDto.getStatus())
                .withMovie(movieService.findMovieFromApiId(viewingMovieRestitDto.getMovieDto().getImdbId()))
                .withAppUser(appUserService.findbyEmail(viewingMovieRestitDto.getEmail()))
                .build();


    }

    @Override
    public ViewingMovie mapCreateDtoToDomain(ViewingMovieCreateDto viewingMovieCreateDto) {
        if ( viewingMovieCreateDto == null ) {
            return null;
        }

        return ViewingMovie.Builder.aViewingMovie()
                .withStatus(viewingMovieCreateDto.getStatus())
                .withMovie(movieService.findMovieFromApiId(viewingMovieCreateDto.getImdbId()))
                .withAppUser(appUserService.findbyEmail(viewingMovieCreateDto.getEmail()))
                .build();
    }

    @Override
    public ViewingMovieRestitDto mapDomainToDto(ViewingMovie viewingMovie) {
        if ( viewingMovie == null ) {
            return null;
        }

        return ViewingMovieRestitDto.Builder.aViewingMovieRestitDto()
                .withStatus(viewingMovie.getStatus())
                .withEmail(viewingMovie.getAppUser().getEmail())
                .withMovieDto(movieDtoMapper.mapDomainToDto(viewingMovie.getMovie()))
                .build();
    }

    @Override
    public ViewingMovieCreateDto mapDomainToCreateDto(ViewingMovie viewingMovie) {
        if ( viewingMovie == null ) {
            return null;
        }

        return ViewingMovieCreateDto.Builder.aViewingMovieCreateDto()
                .withStatus(viewingMovie.getStatus())
                .withEmail(viewingMovie.getAppUser().getEmail())
                .withImdbId(viewingMovie.getMovie().getImdbId())
                .build();
    }

    @Override
    public List<ViewingMovieRestitDto> mapDomainToDto(List<ViewingMovie> viewingMovies) {
        if ( viewingMovies == null ) {
            return null;
        }

        List<ViewingMovieRestitDto> list = new ArrayList<>( viewingMovies.size() );
        for ( ViewingMovie viewingMovie : viewingMovies ) {
            list.add( mapDomainToDto( viewingMovie ) );
        }

        return list;
    }

    @Override
    public List<ViewingMovie> mapDtoToDomain(List<ViewingMovieRestitDto> viewingMovieRestitDtos) {
        if ( viewingMovieRestitDtos == null ) {
            return null;
        }

        List<ViewingMovie> list = new ArrayList<>( viewingMovieRestitDtos.size() );
        for ( ViewingMovieRestitDto viewingMovieRestitDto : viewingMovieRestitDtos ) {
            list.add( mapDtoToDomain( viewingMovieRestitDto ) );
        }

        return list;
    }
}
