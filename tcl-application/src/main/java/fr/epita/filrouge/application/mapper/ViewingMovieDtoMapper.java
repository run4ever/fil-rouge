package fr.epita.filrouge.application.mapper;

import fr.epita.filrouge.application.viewingmovie.ViewingMovieDto;
import fr.epita.filrouge.domain.entity.movie.ViewingMovie;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel="spring")
public interface ViewingMovieDtoMapper {
    ViewingMovie mapDtoToDomain(ViewingMovieDto viewingMovieDto);
    List<ViewingMovie> mapDtoToDomain(List<ViewingMovieDto> viewingMovieDtos);
    ViewingMovieDto mapDomainToDto(ViewingMovie viewingMovie);
    List<ViewingMovieDto> mapDomainToDto(List<ViewingMovie> viewingMovies);
}
