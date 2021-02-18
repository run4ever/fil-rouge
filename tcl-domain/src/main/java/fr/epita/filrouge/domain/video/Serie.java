package fr.epita.filrouge.domain.video;

import java.time.LocalDate;
import java.util.List;

public class Serie extends Video {

    private LocalDate dateStart ;
    private LocalDate dateEnd;
    private Integer numberOfSeason;
    private Integer numberOfEpisode;

    //default constructor visibility package
    Serie() {
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Integer getNumberOfSeason() {
        return numberOfSeason;
    }

    public void setNumberOfSeason(Integer numberOfSeason) {
        this.numberOfSeason = numberOfSeason;
    }

    public Integer getNumberOfEpisode() {
        return numberOfEpisode;
    }

    public void setNumberOfEpisode(Integer numberOfEpisode) {
        this.numberOfEpisode = numberOfEpisode;
    }


    public static final class Builder {
        private LocalDate dateStart ;
        private LocalDate dateEnd;
        private Integer numberOfSeason;
        private Integer numberOfEpisode;
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

        public static Builder aSerie() {
            return new Builder();
        }

        public Builder withDateStart(LocalDate dateStart) {
            this.dateStart = dateStart;
            return this;
        }

        public Builder withDateEnd(LocalDate dateEnd) {
            this.dateEnd = dateEnd;
            return this;
        }

        public Builder withNumberOfSeason(Integer numberOfSeason) {
            this.numberOfSeason = numberOfSeason;
            return this;
        }

        public Builder withNumberOfEpisode(Integer numberOfEpisode) {
            this.numberOfEpisode = numberOfEpisode;
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

        public Serie build() {
            Serie serie = new Serie();
            serie.setDateStart(dateStart);
            serie.setDateEnd(dateEnd);
            serie.setNumberOfSeason(numberOfSeason);
            serie.setNumberOfEpisode(numberOfEpisode);
            serie.setId(id);
            serie.setTitle(title);
            serie.setDescription(description);
            serie.setImageUrl(imageUrl);
            serie.setAverageRating(averageRating);
            serie.setNumberOfVotes(numberOfVotes);
            serie.setCategory(category);
            serie.setActors(actors);
            return serie;
        }
    }
}
