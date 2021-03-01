package fr.epita.filrouge.application.mapper;

import fr.epita.filrouge.application.movie.MovieDto;
import fr.epita.filrouge.domain.entity.common.PublicNotation;
import fr.epita.filrouge.domain.entity.movie.Movie;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public interface MovieDtoMapper {
        public Movie mapDtoToDomain(MovieDto movieDto);
        public List<Movie> mapDtoToDomain(List<MovieDto> movieDtoList);
        public MovieDto mapDomainToDto(Movie movie);
        public List<MovieDto> mapDomainToDto(List<Movie> movieList);
}
