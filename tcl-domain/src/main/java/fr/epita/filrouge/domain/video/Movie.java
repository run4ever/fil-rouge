package fr.epita.filrouge.domain.video;

public class Movie extends Video {

    private MovieFormat movieFormat;

    //default constructor
    public Movie() {
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

        private Builder() {
        }

        public static Builder aMovie() {
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

        public Movie build() {
            Movie movie = new Movie();
            movie.setMovieFormat(movieFormat);
            movie.setId(id);
            movie.setTitle(title);
            movie.setDescription(description);
            movie.setImageUrl(imageUrl);
            movie.setAverageRating(averageRating);
            movie.setNumberOfVotes(numberOfVotes);
            return movie;
        }
    }
}
