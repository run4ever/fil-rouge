package fr.epita.filrouge.domain.video;

import java.util.List;

public class Season {

    private Long id;

    private String seasonTitle;

    private List<Episode> episodes;

    //default constructor visibility package
    Season() {
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

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }


    public static final class Builder {
        private Long id;
        private String seasonTitle;
        private List<Episode> episodes;

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

        public Builder withEpisodes(List<Episode> episodes) {
            this.episodes = episodes;
            return this;
        }

        public Season build() {
            Season season = new Season();
            season.setId(id);
            season.setSeasonTitle(seasonTitle);
            season.setEpisodes(episodes);
            return season;
        }
    }
}
