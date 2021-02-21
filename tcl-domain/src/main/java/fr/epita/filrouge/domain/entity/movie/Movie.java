package fr.epita.filrouge.domain.entity.movie;

import fr.epita.filrouge.domain.entity.common.UserAppreciation;
import fr.epita.filrouge.domain.entity.common.Category;
import fr.epita.filrouge.domain.entity.common.PublicNotation;

import java.time.LocalDate;
import java.util.List;

public class Movie extends Video {

    private Double duration;
    private LocalDate releaseDate;
    private List<UserAppreciation> userAppreciations;
    private PublicNotation publicNotation;
    private String actors;  // on gère les acteurs comme une chaines de caractères
    private Category category;  // Attention, on gère une seul Category ici

    //default constructor
    public Movie() {
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<UserAppreciation> getUserAppreciations() {
        return userAppreciations;
    }

    public void setUserAppreciations(List<UserAppreciation> userAppreciations) {
        this.userAppreciations = userAppreciations;
    }

    public PublicNotation getPublicNotation() {
        return publicNotation;
    }

    public void setPublicNotation(PublicNotation publicNotation) {
        this.publicNotation = publicNotation;
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


    public static final class Builder {
        private Double duration;
        private LocalDate releaseDate;
        private List<UserAppreciation> userAppreciations;
        private PublicNotation publicNotation;
        private String actors;  // on gère les acteurs comme une chaines de caractères
        private Category category;  // Attention, on gère une seul Category ici
        private Long id;
        private String title;
        private String description;
        private String imageUrl;

        private Builder() {
        }

        public static Builder aMovie() {
            return new Builder();
        }

        public Builder withDuration(Double duration) {
            this.duration = duration;
            return this;
        }

        public Builder withReleaseDate(LocalDate releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public Builder withUserAppreciations(List<UserAppreciation> userAppreciations) {
            this.userAppreciations = userAppreciations;
            return this;
        }

        public Builder withPublicNotation(PublicNotation publicNotation) {
            this.publicNotation = publicNotation;
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

        public Movie build() {
            Movie movie = new Movie();
            movie.setDuration(duration);
            movie.setReleaseDate(releaseDate);
            movie.setUserAppreciations(userAppreciations);
            movie.setPublicNotation(publicNotation);
            movie.setActors(actors);
            movie.setCategory(category);
            movie.setId(id);
            movie.setTitle(title);
            movie.setDescription(description);
            movie.setImageUrl(imageUrl);
            return movie;
        }
    }
}
