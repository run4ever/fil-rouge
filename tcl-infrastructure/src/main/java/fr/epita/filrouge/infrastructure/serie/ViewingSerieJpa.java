package fr.epita.filrouge.infrastructure.serie;

import fr.epita.filrouge.infrastructure.person.AppUserJpa;
import fr.epita.filrouge.domain.entity.common.Status;

import javax.persistence.*;

@Entity
@Table(name="serie_viewing")
public class ViewingSerieJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="serie_viewing_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Integer currentSeason;
    private Integer currentEpisode;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private AppUserJpa appUserjpa;


    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private SerieJpa serieJpa;

    public ViewingSerieJpa() {
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

    public AppUserJpa getAppUserjpa() {
        return appUserjpa;
    }

    public void setAppUserjpa(AppUserJpa appUserjpa) {
        this.appUserjpa = appUserjpa;
    }

    public SerieJpa getSerieJpa() {
        return serieJpa;
    }

    public void setSerieJpa(SerieJpa serieJpa) {
        this.serieJpa = serieJpa;
    }

    public static final class Builder {
        private Long id;
        private Status status;
        private Integer currentSeason;
        private Integer currentEpisode;
        private AppUserJpa appUserjpa;
        private SerieJpa serieJpa;

        private Builder() {
        }

        public static Builder aViewingSerieJpa() {
            return new Builder ();
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder withCurrentSeason(Integer currentSeason) {
            this.currentSeason = currentSeason;
            return this;
        }

        public Builder withCurrentEpisode(Integer currentEpisode) {
            this.currentEpisode = currentEpisode;
            return this;
        }

        public Builder withAppUserjpa(AppUserJpa appUserjpa) {
            this.appUserjpa = appUserjpa;
            return this;
        }

        public Builder withSerieJpa(SerieJpa serieJpa) {
            this.serieJpa = serieJpa;
            return this;
        }

        public ViewingSerieJpa build() {
            ViewingSerieJpa viewingSerieJpa = new ViewingSerieJpa ();
            viewingSerieJpa.setId (id);
            viewingSerieJpa.setStatus (status);
            viewingSerieJpa.setCurrentSeason (currentSeason);
            viewingSerieJpa.setCurrentEpisode (currentEpisode);
            viewingSerieJpa.setAppUserjpa (appUserjpa);
            viewingSerieJpa.setSerieJpa (serieJpa);
            return viewingSerieJpa;
        }
    }
}
