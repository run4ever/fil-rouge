package fr.epita.filrouge.domain.video;

import java.time.LocalDate;
import java.util.List;

public class Movie extends Video {

    private MovieFormat movieFormat;
    private LocalDate releaseDate;

    //default constructor visibility package
    Movie() {
    }

    public MovieFormat getMovieFormat() {
        return movieFormat;
    }

    public void setMovieFormat(MovieFormat movieFormat) {
        this.movieFormat = movieFormat;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }


    public static final class Builder {
        private MovieFormat movieFormat;
        private LocalDate releaseDate;
        private Long id;
        private String title;
        private String description;
        private String imageUrl;
        private Double averageRating;
        private Integer numberOfVotes;
        private Category category;
        private List<Actor> actors;

        private Builder() {
        }

        public static Builder aMovie() {
            return new Builder();
        }

        public Builder withMovieFormat(MovieFormat movieFormat) {
            this.movieFormat = movieFormat;
            return this;
        }

        public Builder withReleaseDate(LocalDate releaseDate) {
            this.releaseDate = releaseDate;
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

        public Builder withActors(List<Actor> actors) {
            this.actors = actors;
            return this;
        }

        public Movie build() {
            Movie movie = new Movie();
            movie.setMovieFormat(movieFormat);
            movie.setReleaseDate(releaseDate);
            movie.setId(id);
            movie.setTitle(title);
            movie.setDescription(description);
            movie.setImageUrl(imageUrl);
            movie.setAverageRating(averageRating);
            movie.setNumberOfVotes(numberOfVotes);
            movie.setCategory(category);
            movie.setActors(actors);
            return movie;
        }
    }
}
