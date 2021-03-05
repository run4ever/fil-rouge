package fr.epita.filrouge.domain.entity.serie;

import fr.epita.filrouge.domain.entity.common.Category;
import fr.epita.filrouge.domain.entity.common.PublicNotation;

public class Serie {

    private String imdbId;
    private String title;
    private String description;
    private Integer startYear;
    private Integer endYear;
    private Integer numberOfSeason;
    private Integer numberOfEpisode;
    private Category category; // Attention, on gère une seul Category ici
    private StatusSerie statusSerie;
    private PublicNotation publicNotation;
    private String actors;
    private String imageUrl;

    //default constructor
    public Serie() {
    }

    public Serie(String imdbId, String title, String description, Integer startYear, Integer endYear, Integer numberOfSeason, Integer numberOfEpisode, Category category, StatusSerie statusSerie, PublicNotation publicNotation, String actors, String imageUrl) {
        this.imdbId = imdbId;
        this.title = title;
        this.description = description;
        this.startYear = startYear;
        this.endYear = endYear;
        this.numberOfSeason = numberOfSeason;
        this.numberOfEpisode = numberOfEpisode;
        this.category = category;
        this.statusSerie = statusSerie;
        this.publicNotation = publicNotation;
        this.actors = actors;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }



    @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder ("Builder{");
            sb.append ("imdbId='").append (imdbId).append ('\'');
            sb.append (", title='").append (title).append ('\'');
            sb.append (", description='").append (description).append ('\'');
            sb.append (", startYear=").append (startYear);
            sb.append (", endYear=").append (endYear);
            sb.append (", numberOfSeason=").append (numberOfSeason);
            sb.append (", numberOfEpisode=").append (numberOfEpisode);
            sb.append (", category=").append (category);
            sb.append (", statusSerie=").append (statusSerie);
            sb.append ('}');
            return sb.toString ();
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
        private PublicNotation publicNotation;
        private String actors;
        private String imageUrl;

        private Builder() {
        }

        public static Builder aSerie() {
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

        public Builder withPublicNotation(PublicNotation publicNotation) {
            this.publicNotation = publicNotation;
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

        public Serie build() {
            Serie serie = new Serie();
            serie.setImdbId(imdbId);
            serie.setTitle(title);
            serie.setDescription(description);
            serie.setStartYear(startYear);
            serie.setEndYear(endYear);
            serie.setNumberOfSeason(numberOfSeason);
            serie.setNumberOfEpisode(numberOfEpisode);
            serie.setCategory(category);
            serie.setStatusSerie(statusSerie);
            serie.setPublicNotation(publicNotation);
            serie.setActors(actors);
            serie.setImageUrl(imageUrl);
            return serie;
        }
    }
}
