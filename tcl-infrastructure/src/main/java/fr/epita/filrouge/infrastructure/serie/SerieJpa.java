package fr.epita.filrouge.infrastructure.serie;

import fr.epita.filrouge.infrastructure.domain.entity.common.Category;

import javax.persistence.*;

@Entity
@Table(name="serie")
public class SerieJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="serie_id")
    private Long idSerie;

    private String title;
    private String description;
    private Integer startYear;
    private Integer endYear;
    private Integer numberOfSeason;
    private Integer numberOfEpisode;

    @Enumerated(EnumType.STRING)
    private Category category; // Attention, on gère une seul Category ici

    public SerieJpa() {
    }

    public SerieJpa(Long idSerie, String title, String description, Integer startYear, Integer endYear, Integer numberOfSeason, Integer numberOfEpisode, Category category) {
        this.idSerie = idSerie;
        this.title = title;
        this.description = description;
        this.startYear = startYear;
        this.endYear = endYear;
        this.numberOfSeason = numberOfSeason;
        this.numberOfEpisode = numberOfEpisode;
        this.category = category;
    }

    public Long getId() {
        return idSerie;
    }

    public void setId(Long id) {
        this.idSerie = id;
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


    public static final class Builder {
        private Long idSerie;
        private String title;
        private String description;
        private Integer startYear;
        private Integer endYear;
        private Integer numberOfSeason;
        private Integer numberOfEpisode;

        @Enumerated(EnumType.STRING)
        private Category category; // Attention, on gère une seul Category ici

        private Builder() {
        }

        public static Builder aSerieJpa() {
            return new Builder ();
        }

        public Builder withId(Long id) {
            this.idSerie = id;
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

        public SerieJpa build() {
            SerieJpa serieJpa = new SerieJpa ();
            serieJpa.setId (idSerie);
            serieJpa.setTitle (title);
            serieJpa.setDescription (description);
            serieJpa.setStartYear (startYear);
            serieJpa.setEndYear (endYear);
            serieJpa.setNumberOfSeason (numberOfSeason);
            serieJpa.setNumberOfEpisode (numberOfEpisode);
            serieJpa.setCategory (category);
            return serieJpa;
        }
    }
}
