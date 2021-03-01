package fr.epita.filrouge.application.mapper;

import fr.epita.filrouge.application.movie.MovieDto;
import fr.epita.filrouge.domain.entity.movie.Movie;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface MovieDtoMapper {

        Movie mapDtoToDomain(MovieDto movieDto);
        List<Movie> mapDtoToDomain(List<MovieDto> movieDtoList);
        MovieDto mapDomainToDto(Movie movie);
        List<MovieDto> mapDomainToDto(List<Movie> movieList);

}
