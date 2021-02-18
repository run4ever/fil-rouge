package fr.epita.filrouge.domain.person;

import fr.epita.filrouge.domain.video.Video;


public class UserPlaylist {

    private Long id;
    private AppUser user;
    private Video video;
    private Integer notationUser;
    private Status status;
    private Integer seasonNumber;
    private Integer episodeNumber;


    //default constructor visibility package
    UserPlaylist() {   }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Integer getNotationUser() {
        return notationUser;
    }

    public void setNotationUser(Integer notationUser) {
        this.notationUser = notationUser;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
        private AppUser user;
        private Video video;
        private Integer notationUser;
        private Status status;
        private Integer seasonNumber;
        private Integer episodeNumber;

        private Builder() {
        }

        public static Builder anUserPlaylist() {
            return new Builder();
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withUser(AppUser user) {
            this.user = user;
            return this;
        }

        public Builder withVideo(Video video) {
            this.video = video;
            return this;
        }

        public Builder withNotationUser(Integer notationUser) {
            this.notationUser = notationUser;
            return this;
        }

        public Builder withStatus(Status status) {
            this.status = status;
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

        public UserPlaylist build() {
            UserPlaylist userPlaylist = new UserPlaylist();
            userPlaylist.setId(id);
            userPlaylist.setUser(user);
            userPlaylist.setVideo(video);
            userPlaylist.setNotationUser(notationUser);
            userPlaylist.setStatus(status);
            userPlaylist.setSeasonNumber(seasonNumber);
            userPlaylist.setEpisodeNumber(episodeNumber);
            return userPlaylist;
        }
    }
}
