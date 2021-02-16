package fr.epita.filrouge.domain.video;

import java.util.List;

public class Season {

    private Long id;

    private String seasonTitle;

    private List<Episode> numberOfEpisode;

    //default constructor visibility package
    Season() {
    }

    public Season(Long id, String seasonTitle, List<Episode> numberOfEpisode) {
        this.id = id;
        this.seasonTitle = seasonTitle;
        this.numberOfEpisode = numberOfEpisode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeasonTitle() {
        return seasonTitle;
    }

    public void setSeasonTitle(String seasonTitle) {
        this.seasonTitle = seasonTitle;
    }

    public List<Episode> getNumberOfEpisode() {
        return numberOfEpisode;
    }

    public void setNumberOfEpisode(List<Episode> numberOfEpisode) {
        this.numberOfEpisode = numberOfEpisode;
    }


    public static final class Builder {
        private Long id;
        private String seasonTitle;
        private List<Episode> numberOfEpisode;

        private Builder() {
        }

        public static Builder aSeason() {
            return new Builder();
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withSeasonTitle(String seasonTitle) {
            this.seasonTitle = seasonTitle;
            return this;
        }

        public Builder withNumberOfEpisode(List<Episode> numberOfEpisode) {
            this.numberOfEpisode = numberOfEpisode;
            return this;
        }

        public Season build() {
            Season season = new Season();
            season.setId(id);
            season.setSeasonTitle(seasonTitle);
            season.setNumberOfEpisode(numberOfEpisode);
            return season;
        }
    }
}
