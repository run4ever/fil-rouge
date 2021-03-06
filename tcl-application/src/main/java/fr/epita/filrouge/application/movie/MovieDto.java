package fr.epita.filrouge.application.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import fr.epita.filrouge.domain.entity.common.Category;

import java.time.LocalDate;

public class MovieDto {

    @JsonProperty("imdbId")
    @NotNull
    private String imdbId;

    @JsonProperty("title")
    @NotNull
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("category")
    @NotNull
    private Category category;

    @JsonProperty("startYear")
    private LocalDate releaseDate;

    @JsonProperty("imdbRating")
    private Double averageRating;

    @JsonProperty("imdbVotes")
    private Integer numberOfVotes;

    @JsonProperty("actors")
    private String actors;

    @JsonProperty("imageUrl")
    private String imageUrl;

    //données spécifique de Movie
    @JsonProperty("runtime")
    private Integer duration;

    public MovieDto() {
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(Integer numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public static final class Builder {
        private String imdbId;
        private LocalDate releaseDate;
        private Double averageRating;
        private Integer numberOfVotes;
        private String actors;
        private Category category;
        private String title;
        private String description;
        private String imageUrl;
        private Integer duration;

        private Builder() {
        }

        public static Builder aMovieDto() {
            return new Builder();
        }

        public Builder withImdbId(String imdbId) {
            this.imdbId = imdbId;
            return this;
        }

        public Builder withReleaseDate(LocalDate releaseDate) {
            this.releaseDate = releaseDate;
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

        public Builder withActors(String actors) {
            this.actors = actors;
            return this;
        }

        public Builder withCategory(Category category) {
            this.category = category;
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

        public Builder withDuration(Integer duration) {
            this.duration = duration;
            return this;
        }

        public MovieDto build() {
            MovieDto movieDto = new MovieDto();
            movieDto.setImdbId(imdbId);
            movieDto.setReleaseDate(releaseDate);
            movieDto.setAverageRating(averageRating);
            movieDto.setNumberOfVotes(numberOfVotes);
            movieDto.setActors(actors);
            movieDto.setCategory(category);
            movieDto.setTitle(title);
            movieDto.setDescription(description);
            movieDto.setImageUrl(imageUrl);
            movieDto.setDuration(duration);
            return movieDto;
        }
    }
}
