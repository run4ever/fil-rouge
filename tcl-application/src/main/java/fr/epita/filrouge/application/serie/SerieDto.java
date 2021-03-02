package fr.epita.filrouge.application.serie;



import fr.epita.filrouge.domain.entity.common.Category;
import fr.epita.filrouge.domain.entity.serie.StatusSerie;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class SerieDto {

    //id unique de série (et aussi de film)
    //il commence par "tt"

    @Pattern(regexp = "^tt")
    private String imdbId;
    @NotNull
    private String title;
    @NotNull
    private String description;
    private Integer startYear;
    private Integer endYear;
    private Integer numberOfSeason;
    private Integer numberOfEpisode;
    private Category category; // Attention, on gère une seul Category ici
    private StatusSerie statusSerie;


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


    public static final class Builder {
        private String imdbId;
        private String title;
        private String description;
        private Integer startYear;
        private Integer endYear;
        private Integer numberOfSeason;
        private Integer numberOfEpisode;
        private Category category; // Attention, on gère une seul Category ici
        private StatusSerie statusSerie;

        private Builder() {
        }

        public static Builder aSerieDto() {
            return new Builder();
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

        public SerieDto build() {
            SerieDto serieDto = new SerieDto();
            serieDto.setImdbId(imdbId);
            serieDto.setTitle(title);
            serieDto.setDescription(description);
            serieDto.setStartYear(startYear);
            serieDto.setEndYear(endYear);
            serieDto.setNumberOfSeason(numberOfSeason);
            serieDto.setNumberOfEpisode(numberOfEpisode);
            serieDto.setCategory(category);
            serieDto.setStatusSerie(statusSerie);
            return serieDto;
        }
    }
}
