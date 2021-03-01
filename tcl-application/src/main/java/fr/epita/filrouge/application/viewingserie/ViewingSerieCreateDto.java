package fr.epita.filrouge.application.viewingserie;

import fr.epita.filrouge.domain.entity.common.Status;

import javax.validation.constraints.NotNull;

public class ViewingSerieCreateDto {

    @NotNull
    private Status status;
    @NotNull
    private Integer currentSeason;
    @NotNull
    private Integer currentEpisode;

    private String imdb;
    private String email;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getCurrentSeason() {
        return currentSeason;
    }

    public void setCurrentSeason(Integer currentSeason) {
        this.currentSeason = currentSeason;
    }

    public Integer getCurrentEpisode() {
        return currentEpisode;
    }

    public void setCurrentEpisode(Integer currentEpisode) {
        this.currentEpisode = currentEpisode;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
