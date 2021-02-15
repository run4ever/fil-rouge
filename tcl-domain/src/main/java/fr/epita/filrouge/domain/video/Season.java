package fr.epita.filrouge.domain.video;

import java.util.List;

public class Season {

    private long id;

    private String seasonTitle;

    private List<Episode> numberOfEpisode;

    // default constructor for spring


    Season() {
    }

    public Season(long id, String seasonTitle, List<Episode> numberOfEpisode) {
        this.id = id;
        this.seasonTitle = seasonTitle;
        this.numberOfEpisode = numberOfEpisode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
