package fr.epita.filrouge.domain.entity.movie;


import fr.epita.filrouge.domain.entity.common.Status;
import fr.epita.filrouge.domain.entity.person.AppUser;

public class ViewingMovie {
    private Long id;
    private Status status;
    private AppUser appUser;
    private Movie movie;

    //default constructor
    public ViewingMovie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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


    public static final class Builder {
        private Long id;
        private Status status;
        private AppUser appUser;
        private Movie movie;

        private Builder() {
        }

        public static Builder aViewingMovie() {
            return new Builder();
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withStatus(Status status) {
            this.status = status;
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

        public ViewingMovie build() {
            ViewingMovie viewingMovie = new ViewingMovie();
            viewingMovie.setId(id);
            viewingMovie.setStatus(status);
            viewingMovie.setAppUser(appUser);
            viewingMovie.setMovie(movie);
            return viewingMovie;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ViewingMovie that = (ViewingMovie) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder().append(id, that.id).append(status, that.status).append(appUser, that.appUser).append(movie, that.movie).isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37).append(id).append(status).append(appUser).append(movie).toHashCode();
    }
}
