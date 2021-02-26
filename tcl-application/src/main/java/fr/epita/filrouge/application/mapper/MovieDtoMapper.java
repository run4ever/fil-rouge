package fr.epita.filrouge.application.mapper;

import fr.epita.filrouge.application.movie.MovieDto;
import fr.epita.filrouge.domain.entity.movie.Movie;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface MovieDtoMapper {

        Movie mapToEntity(MovieDto movieDto);
        List<Movie> mapToEntity(List<MovieDto> movieDtoList);
        MovieDto mapToDto(Movie movie);
        List<MovieDto> mapToDto(List<Movie> movieList);

}
