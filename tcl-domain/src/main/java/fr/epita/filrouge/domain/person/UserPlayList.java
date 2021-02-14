package fr.epita.filrouge.domain.person;

import fr.epita.filrouge.domain.video.Episode;
import fr.epita.filrouge.domain.video.Movie;

import java.util.List;

public class UserPlayList {
    private Long id;

    private int notationUser;

    private Status status;

    private List<Movie> moviesList;

    private List<Episode> episodeList;

    public Long getId() {
        return id;
    }
    //constructeur par défaut pour Spring
    UserPlayList() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNotationUser() {
        return notationUser;
    }

    public void setNotationUser(int notationUser) {
        this.notationUser = notationUser;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Movie> getMoviesList() {
        return moviesList;
    }

    public void setMoviesList(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    public List<Episode> getEpisodeList() {
        return episodeList;
    }

    public void setEpisodeList(List<Episode> episodeList) {
        this.episodeList = episodeList;
    }

}
