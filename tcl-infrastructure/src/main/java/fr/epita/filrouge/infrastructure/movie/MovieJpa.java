package fr.epita.filrouge.infrastructure.movie;

import fr.epita.filrouge.domain.entity.common.Category;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="movie")
public class MovieJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="movie_id")
    private Long id;

    @Column(unique = true)
    private String imdbId;

    private String title;
    private String description;
    private String imageUrl;

    private Integer duration;
    private LocalDate releaseDate;

    private Double averageRating;
    private Integer numberOfVotes;

    private String actors;

    @Enumerated(EnumType.STRING)
    private Category category;

    public MovieJpa() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
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

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }


    public static final class Builder {
        private Long id;
        private String imdbId;
        private String title;
        private String description;
        private String imageUrl;
        private Integer duration;
        private LocalDate releaseDate;
        private Double averageRating;
        private Integer numberOfVotes;
        private String actors;
        private Category category;

        private Builder() {
        }

        public static Builder aMovieJpa() {
            return new Builder();
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withImdbId(String imdbId) {
            this.imdbId = imdbId;
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

        public MovieJpa build() {
            MovieJpa movieJpa = new MovieJpa();
            movieJpa.setId(id);
            movieJpa.setImdbId(imdbId);
            movieJpa.setTitle(title);
            movieJpa.setDescription(description);
            movieJpa.setImageUrl(imageUrl);
            movieJpa.setDuration(duration);
            movieJpa.setReleaseDate(releaseDate);
            movieJpa.setAverageRating(averageRating);
            movieJpa.setNumberOfVotes(numberOfVotes);
            movieJpa.setActors(actors);
            movieJpa.setCategory(category);
            return movieJpa;
        }
    }
}
