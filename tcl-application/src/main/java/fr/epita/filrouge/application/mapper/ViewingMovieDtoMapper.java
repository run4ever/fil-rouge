package fr.epita.filrouge.application.mapper;

import fr.epita.filrouge.application.viewingmovie.ViewingMovieCreateDto;
import fr.epita.filrouge.application.viewingmovie.ViewingMovieRestitDto;
import fr.epita.filrouge.domain.entity.movie.ViewingMovie;

import java.util.List;

public interface ViewingMovieDtoMapper {
    ViewingMovie mapDtoToDomain(ViewingMovieRestitDto viewingMovieRestitDto);
    ViewingMovie mapCreateDtoToDomain(ViewingMovieCreateDto viewingMovieCreateDto);
    List<ViewingMovie> mapDtoToDomain(List<ViewingMovieRestitDto> viewingMovieRestitDtos);
    ViewingMovieRestitDto mapDomainToDto(ViewingMovie viewingMovie);
    ViewingMovieCreateDto mapDomainToCreateDto(ViewingMovie viewingMovie);
    List<ViewingMovieRestitDto> mapDomainToDto(List<ViewingMovie> viewingMovies);
}
