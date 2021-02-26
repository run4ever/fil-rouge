package fr.epita.filrouge.infrastructure.movie;

import fr.epita.filrouge.domain.entity.common.Category;
import fr.epita.filrouge.domain.entity.common.PublicNotation;
import fr.epita.filrouge.domain.entity.movie.Movie;
import fr.epita.filrouge.domain.entity.movie.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MovieTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void add_new_movie_should_success()  {
        //Given
        final Movie movie1 = Movie.Builder.aMovie()
                .withTitle("Indiana Jones and the Raiders of the Lost Ark")
                .withPublicNotation(new PublicNotation(4.0,1234))
                .withActors("Harrison Ford, Karen Allen, Paul Freeman, Ronald Lacey")
                .withDuration(115)
                .withCategory(Category.ACTION)
                .withDescription("In 1936, archaeologist and adventurer Indiana Jones is hired by the U.S. government to find the Ark of the Covenant before Nazis can obtain its awesome powers.")
                .withReleaseDate(LocalDate.now())
                .build();

        // When
        movieRepository.create(movie1);

        // Then
        assertThat(movieRepository.findMovieFromTitle(movie1.getTitle())).isNotNull();
    }

}
