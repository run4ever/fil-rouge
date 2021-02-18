package fr.epita.filrouge.infrastructure.video;

import fr.epita.filrouge.domain.video.Category;
import fr.epita.filrouge.infrastructure.person.ActorJPA;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

@Entity(name="serie")
public class SerieJPA extends VideoJPA {
    private LocalDate dateStart ;
    private LocalDate dateEnd;
    private Integer numberOfSeason;
    private Integer numberOfEpisode;

    public SerieJPA() {
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
        private List<ActorJPA> actors;

        private Builder() {
        }

        public static Builder aSerieJPA() {
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

        public Builder withActors(List<ActorJPA> actors) {
            this.actors = actors;
            return this;
        }

        public SerieJPA build() {
            SerieJPA serieJPA = new SerieJPA();
            serieJPA.setDateStart(dateStart);
            serieJPA.setDateEnd(dateEnd);
            serieJPA.setNumberOfSeason(numberOfSeason);
            serieJPA.setNumberOfEpisode(numberOfEpisode);
            serieJPA.setId(id);
            serieJPA.setTitle(title);
            serieJPA.setDescription(description);
            serieJPA.setImageUrl(imageUrl);
            serieJPA.setAverageRating(averageRating);
            serieJPA.setNumberOfVotes(numberOfVotes);
            serieJPA.setCategory(category);
            serieJPA.setActors(actors);
            return serieJPA;
        }
    }
}
