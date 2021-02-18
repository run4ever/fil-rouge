package fr.epita.filrouge.domain.person;

import fr.epita.filrouge.domain.video.Movie;

public class UserPlaylistMovie {
    private Long id;
    private AppUser appUser;
    private Movie movie;
    private Integer notationUser;
    private Status status;

    //default constructor visibility package
    UserPlaylistMovie() {
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

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
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


    public static final class Builder {
        private Long id;
        private AppUser appUser;
        private Movie movie;
        private Integer notationUser;
        private Status status;

        private Builder() {
        }

        public static Builder anUserPlaylistMovie() {
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

        public Builder withMovie(Movie movie) {
            this.movie = movie;
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

        public UserPlaylistMovie build() {
            UserPlaylistMovie userPlaylistMovie = new UserPlaylistMovie();
            userPlaylistMovie.setId(id);
            userPlaylistMovie.setAppUser(appUser);
            userPlaylistMovie.setMovie(movie);
            userPlaylistMovie.setNotationUser(notationUser);
            userPlaylistMovie.setStatus(status);
            return userPlaylistMovie;
        }
    }
}
