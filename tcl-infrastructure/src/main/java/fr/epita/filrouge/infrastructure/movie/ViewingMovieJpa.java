package fr.epita.filrouge.infrastructure.movie;


import fr.epita.filrouge.domain.entity.common.Status;
import fr.epita.filrouge.domain.entity.movie.Movie;
import fr.epita.filrouge.infrastructure.person.AppUserJpa;

import javax.persistence.*;

@Entity
@Table(name="movie_viewing")
public class ViewingMovieJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="movie_viewing_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private AppUserJpa appUserJpa;

    @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private MovieJpa movieJpa;

    public ViewingMovieJpa() {
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

    public AppUserJpa getAppUserJpa() {
        return appUserJpa;
    }

    public void setAppUserJpa(AppUserJpa appUserJpa) {
        this.appUserJpa = appUserJpa;
    }

    public MovieJpa getMovieJpa() {
        return movieJpa;
    }

    public void setMovieJpa(MovieJpa movieJpa) {
        this.movieJpa = movieJpa;
    }


    public static final class Builder {
        private Long id;
        private Status status;
        private AppUserJpa appUserJpa;
        private MovieJpa movieJpa;

        private Builder() {
        }

        public static Builder aViewingMovieJpa() {
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

        public Builder withAppUserJpa(AppUserJpa appUserJpa) {
            this.appUserJpa = appUserJpa;
            return this;
        }

        public Builder withMovieJpa(MovieJpa movieJpa) {
            this.movieJpa = movieJpa;
            return this;
        }

        public ViewingMovieJpa build() {
            ViewingMovieJpa viewingMovieJpa = new ViewingMovieJpa();
            viewingMovieJpa.setId(id);
            viewingMovieJpa.setStatus(status);
            viewingMovieJpa.setAppUserJpa(appUserJpa);
            viewingMovieJpa.setMovieJpa(movieJpa);
            return viewingMovieJpa;
        }
    }
}
