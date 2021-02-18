package fr.epita.filrouge.domain.person;

import fr.epita.filrouge.domain.video.Serie;

public class UserPlaylistSerie {
    private Long id;
    private AppUser appUser;
    private Serie serie;
    private Integer seasonNumber; //le numéro sasion que l'utilisateur est en train de regarder
    private Integer episodeNumber; //le numéro d'épisode que l'utilisateur est en train de regarder

    //default constructor visibility package
    UserPlaylistSerie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }


    public static final class Builder {
        private Long id;
        private AppUser appUser;
        private Serie serie;
        private Integer seasonNumber; //le numéro sasion que l'utilisateur est en train de regarder
        private Integer episodeNumber; //le numéro d'épisode que l'utilisateur est en train de regarder

        private Builder() {
        }

        public static Builder anUserPlaylistSerie() {
            return new Builder();
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withAppUser(AppUser appUser) {
            this.appUser = appUser;
            return this;
        }

        public Builder withSerie(Serie serie) {
            this.serie = serie;
            return this;
        }

        public Builder withSeasonNumber(Integer seasonNumber) {
            this.seasonNumber = seasonNumber;
            return this;
        }

        public Builder withEpisodeNumber(Integer episodeNumber) {
            this.episodeNumber = episodeNumber;
            return this;
        }

        public UserPlaylistSerie build() {
            UserPlaylistSerie userPlaylistSerie = new UserPlaylistSerie();
            userPlaylistSerie.setId(id);
            userPlaylistSerie.setAppUser(appUser);
            userPlaylistSerie.setSerie(serie);
            userPlaylistSerie.setSeasonNumber(seasonNumber);
            userPlaylistSerie.setEpisodeNumber(episodeNumber);
            return userPlaylistSerie;
        }
    }
}
