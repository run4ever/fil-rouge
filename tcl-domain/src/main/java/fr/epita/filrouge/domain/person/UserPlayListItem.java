package fr.epita.filrouge.domain.person;

import fr.epita.filrouge.domain.video.Episode;
import fr.epita.filrouge.domain.video.Movie;
import fr.epita.filrouge.domain.video.Video;

public class UserPlayListItem {

    private Long id;

    private Movie movie;

    private Episode episode;

    private int notationUser;

    private Status status;

    //default constructor
    public UserPlayListItem() {
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

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }


    public static final class Builder {
        private Long id;
        private Movie movie;
        private Episode episode;
        private int notationUser;
        private Status status;

        private Builder() {
        }

        public static Builder anUserPlayListItem() {
            return new Builder();
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withMovie(Movie movie) {
            this.movie = movie;
            return this;
        }

        public Builder withEpisode(Episode episode) {
            this.episode = episode;
            return this;
        }

        public Builder withNotationUser(int notationUser) {
            this.notationUser = notationUser;
            return this;
        }

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public UserPlayListItem build() {
            UserPlayListItem userPlayListItem = new UserPlayListItem();
            userPlayListItem.setMovie(movie);
            userPlayListItem.setEpisode(episode);
            userPlayListItem.setNotationUser(notationUser);
            userPlayListItem.setStatus(status);
            userPlayListItem.id = this.id;
            return userPlayListItem;
        }
    }
}
