package fr.epita.filrouge.infrastructure.video;

import fr.epita.filrouge.domain.video.Category;
import fr.epita.filrouge.domain.video.MovieFormat;
import fr.epita.filrouge.infrastructure.person.ActorJPA;

import javax.persistence.Entity;
import java.util.List;

@Entity(name = "movie")
public class MovieJPA extends VideoJPA {
    private MovieFormat movieFormat;

    public MovieJPA() {

    }

    public MovieFormat getMovieFormat() {
        return movieFormat;
    }

    public void setMovieFormat(MovieFormat movieFormat) {
        this.movieFormat = movieFormat;
    }


    public static final class Builder {
        private MovieFormat movieFormat;
        private Long id;
        private String title;
        private String description;
        private String imageUrl;
        private Double averageRating;
        private Integer numberOfVotes;
        private Category category;
        private List<ActorJPA> actors;

        private Builder() {
        }

        public static Builder aMovieJPA() {
            return new Builder();
        }

        public Builder withMovieFormat(MovieFormat movieFormat) {
            this.movieFormat = movieFormat;
            return this;
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder withAverageRating(Double averageRating) {
            this.averageRating = averageRating;
            return this;
        }

        public Builder withNumberOfVotes(Integer numberOfVotes) {
            this.numberOfVotes = numberOfVotes;
            return this;
        }

        public Builder withCategory(Category category) {
            this.category = category;
            return this;
        }

        public Builder withActors(List<ActorJPA> actors) {
            this.actors = actors;
            return this;
        }

        public MovieJPA build() {
            MovieJPA movieJPA = new MovieJPA();
            movieJPA.setMovieFormat(movieFormat);
            movieJPA.setId(id);
            movieJPA.setTitle(title);
            movieJPA.setDescription(description);
            movieJPA.setImageUrl(imageUrl);
            movieJPA.setAverageRating(averageRating);
            movieJPA.setNumberOfVotes(numberOfVotes);
            movieJPA.setCategory(category);
            movieJPA.setActors(actors);
            return movieJPA;
        }
    }
}
