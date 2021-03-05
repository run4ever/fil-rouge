package fr.epita.filrouge.infrastructure.serie;

import fr.epita.filrouge.domain.entity.common.Category;
import fr.epita.filrouge.domain.entity.serie.StatusSerie;

import javax.persistence.*;

@Entity
@Table(name="serie")
public class SerieJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="serie_id")
    private Long idSerie;


    @Column(unique = true)
    private String imdbId;

    private String title;

    private String description;
    private Integer startYear;
    private Integer endYear;
    private Integer numberOfSeason;
    private Integer numberOfEpisode;

    @Enumerated(EnumType.STRING)
    private Category category; // Attention, on gère une seul Category ici

    @Enumerated(EnumType.STRING)
    private StatusSerie statusSerie;

    private Double averageRating;
    private Integer numberOfVotes;
    private String imageUrl;
    private String actors;

    public SerieJpa() {
    }

    public SerieJpa(Long idSerie, String imdbId, String title, String description, Integer startYear, Integer endYear, Integer numberOfSeason, Integer numberOfEpisode, Category category, StatusSerie statusSerie, Double averageRating, Integer numberOfVotes, String imageUrl, String actors) {
        this.idSerie = idSerie;
        this.imdbId = imdbId;
        this.title = title;
        this.description = description;
        this.startYear = startYear;
        this.endYear = endYear;
        this.numberOfSeason = numberOfSeason;
        this.numberOfEpisode = numberOfEpisode;
        this.category = category;
        this.statusSerie = statusSerie;
        this.averageRating = averageRating;
        this.numberOfVotes = numberOfVotes;
        this.imageUrl = imageUrl;
        this.actors = actors;
    }

    public Long getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(Long idSerie) {
        this.idSerie = idSerie;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
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

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public StatusSerie getStatusSerie() {
        return statusSerie;
    }

    public void setStatusSerie(StatusSerie statusSerie) {
        this.statusSerie = statusSerie;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }


    public static final class Builder {
        private Long idSerie;
        private String imdbId;
        private String title;
        private String description;
        private Integer startYear;
        private Integer endYear;
        private Integer numberOfSeason;
        private Integer numberOfEpisode;
        private Category category; // Attention, on gère une seul Category ici
        private StatusSerie statusSerie;
        private Double averageRating;
        private Integer numberOfVotes;
        private String imageUrl;
        private String actors;

        private Builder() {
        }

        public static Builder aSerieJpa() {
            return new Builder();
        }

        public Builder withIdSerie(Long idSerie) {
            this.idSerie = idSerie;
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

        public Builder withStartYear(Integer startYear) {
            this.startYear = startYear;
            return this;
        }

        public Builder withEndYear(Integer endYear) {
            this.endYear = endYear;
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

        public Builder withCategory(Category category) {
            this.category = category;
            return this;
        }

        public Builder withStatusSerie(StatusSerie statusSerie) {
            this.statusSerie = statusSerie;
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

        public Builder withImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder withActors(String actors) {
            this.actors = actors;
            return this;
        }

        public SerieJpa build() {
            SerieJpa serieJpa = new SerieJpa();
            serieJpa.setIdSerie(idSerie);
            serieJpa.setImdbId(imdbId);
            serieJpa.setTitle(title);
            serieJpa.setDescription(description);
            serieJpa.setStartYear(startYear);
            serieJpa.setEndYear(endYear);
            serieJpa.setNumberOfSeason(numberOfSeason);
            serieJpa.setNumberOfEpisode(numberOfEpisode);
            serieJpa.setCategory(category);
            serieJpa.setStatusSerie(statusSerie);
            serieJpa.setAverageRating(averageRating);
            serieJpa.setNumberOfVotes(numberOfVotes);
            serieJpa.setImageUrl(imageUrl);
            serieJpa.setActors(actors);
            return serieJpa;
        }
    }
}
