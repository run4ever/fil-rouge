package fr.epita.filrouge.infrastructure.viewingserie;

import fr.epita.filrouge.infrastructure.person.AppUserJpa;
import fr.epita.filrouge.domain.entity.common.Status;
import fr.epita.filrouge.infrastructure.serie.SerieJpa;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="serie_viewing")
public class ViewingSerieJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="serie_viewing_id", unique = true)
    private Long idViewSerie;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Integer currentSeason = 1;
    private Integer currentEpisode = 1;

    @ManyToOne(fetch = FetchType.EAGER)
    private AppUserJpa appUserJpa;


    @ManyToOne(fetch = FetchType.EAGER)
    private SerieJpa serieJpa;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date dateLastAction;

    public ViewingSerieJpa() {
        //default constructor
    }

    public Long getId() {
        return idViewSerie;
    }

    public void setId(Long id) {
        this.idViewSerie = id;
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

    public AppUserJpa getAppUserJpa() {
        return appUserJpa;
    }

    public void setAppUserJpa(AppUserJpa appUserjpa) {
        this.appUserJpa = appUserjpa;
    }

    public SerieJpa getSerieJpa() {
        return serieJpa;
    }

    public void setSerieJpa(SerieJpa serieJpa) {
        this.serieJpa = serieJpa;
    }

    public Date getDateLastAction() {
        return dateLastAction;
    }

    public void setDateLastAction(Date dateLastAction) {
        this.dateLastAction = dateLastAction;
    }

    public static final class Builder {
        private Long idViewSerie;
        private Status status;
        private Integer currentSeason;
        private Integer currentEpisode;
        private AppUserJpa appUserjpa;
        private SerieJpa serieJpa;
        private Date dateLastAction;

        private Builder() {
        }

        public static Builder aViewingSerieJpa() {
            return new Builder ();
        }

        public Builder(Long idViewSerie) {
            this.idViewSerie = idViewSerie;
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

        public Builder withDateLastAction(Date dateLastAction) {
            this.dateLastAction = dateLastAction;
            return this;
        }

        public ViewingSerieJpa build() {
            ViewingSerieJpa viewingSerieJpa = new ViewingSerieJpa ();
            viewingSerieJpa.setId (idViewSerie);
            viewingSerieJpa.setStatus (status);
            viewingSerieJpa.setCurrentSeason (currentSeason);
            viewingSerieJpa.setCurrentEpisode (currentEpisode);
            viewingSerieJpa.setAppUserJpa (appUserjpa);
            viewingSerieJpa.setSerieJpa (serieJpa);
            viewingSerieJpa.setDateLastAction (dateLastAction);
            return viewingSerieJpa;
        }
    }
}
