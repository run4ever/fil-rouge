package fr.epita.filrouge.domain.entity.serie;

import fr.epita.filrouge.domain.entity.common.Category;

public class Serie {

    private Long id;
    private String title;
    private String description;
    private Integer startYear;
    private Integer endYear;
    private Integer numberOfSeason;
    private Integer numberOfEpisode;
    private Category category; // Attention, on gère une seul Category ici

    //default constructor
    public Serie() {
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
        private Long id;
        private String title;
        private String description;
        private Integer startYear;
        private Integer endYear;
        private Integer numberOfSeason;
        private Integer numberOfEpisode;
        private Category category; // Attention, on gère Serie est dans une seule Category

        private Builder() {
        }

        public static Builder aSerie() {
            return new Builder();
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

        public Serie build() {
            Serie serie = new Serie();
            serie.setId(id);
            serie.setTitle(title);
            serie.setDescription(description);
            serie.setStartYear(startYear);
            serie.setEndYear(endYear);
            serie.setNumberOfSeason(numberOfSeason);
            serie.setNumberOfEpisode(numberOfEpisode);
            serie.setCategory(category);
            return serie;
        }
    }
}
