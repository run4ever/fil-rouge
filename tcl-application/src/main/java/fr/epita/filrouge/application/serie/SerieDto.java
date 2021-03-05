package fr.epita.filrouge.application.serie;



import com.fasterxml.jackson.annotation.JsonProperty;
import fr.epita.filrouge.domain.entity.common.Category;
import fr.epita.filrouge.domain.entity.serie.StatusSerie;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class SerieDto {

    //id unique de série (et aussi de film)
    //il commence par "tt"

    @JsonProperty("imdbId")
    @Pattern(regexp = "^tt")
    private String imdbId;

    @JsonProperty("title")
    @NotNull
    private String title;

    @JsonProperty("description")
    @NotNull
    private String description;

    @JsonProperty("category")
    private Category category; // Attention, on gère une seul Category ici

    @JsonProperty("startYear")
    private Integer startYear;

    @JsonProperty("imdbRating")
    private Double averageRating;

    @JsonProperty("imdbVotes")
    private Integer numberOfVotes;

    @JsonProperty("actors")
    private String actors;

    @JsonProperty("imageUrl")
    private String imageUrl;

    //données spécifique de Série
    @JsonProperty("endYear")
    private Integer endYear;

    @JsonProperty("numberOfSeason")
    private Integer numberOfSeason;

    @JsonProperty("numberOfEpisode")
    private Integer numberOfEpisode;

    @JsonProperty("statusSerie")
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
        private Category category; // Attention, on gère une seul Category ici
        private Integer startYear;
        private Double averageRating;
        private Integer numberOfVotes;
        private String actors;
        private String imageUrl;
        //données spécifique de Série
        private Integer endYear;
        private Integer numberOfSeason;
        private Integer numberOfEpisode;
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

        public Builder withCategory(Category category) {
            this.category = category;
            return this;
        }

        public Builder withStartYear(Integer startYear) {
            this.startYear = startYear;
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

        public Builder withImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
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

        public Builder withStatusSerie(StatusSerie statusSerie) {
            this.statusSerie = statusSerie;
            return this;
        }

        public SerieDto build() {
            SerieDto serieDto = new SerieDto();
            serieDto.setImdbId(imdbId);
            serieDto.setTitle(title);
            serieDto.setDescription(description);
            serieDto.setCategory(category);
            serieDto.setStartYear(startYear);
            serieDto.setAverageRating(averageRating);
            serieDto.setNumberOfVotes(numberOfVotes);
            serieDto.setActors(actors);
            serieDto.setImageUrl(imageUrl);
            serieDto.setEndYear(endYear);
            serieDto.setNumberOfSeason(numberOfSeason);
            serieDto.setNumberOfEpisode(numberOfEpisode);
            serieDto.setStatusSerie(statusSerie);
            return serieDto;
        }
    }
}
