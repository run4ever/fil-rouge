package fr.epita.filrouge.domain.entity.viewingserie;


import fr.epita.filrouge.domain.entity.common.Status;
import fr.epita.filrouge.domain.entity.person.AppUser;
import fr.epita.filrouge.domain.entity.serie.Serie;

import java.time.LocalDate;

public class ViewingSerie {

    private Status status;
    private Integer currentSeason;
    private Integer currentEpisode;
    private AppUser appUser;
    private Serie serie;
    private LocalDate dateLastAction;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder ("ViewingSerie{");
        sb.append ("status=").append (status);
        sb.append (", currentSeason=").append (currentSeason);
        sb.append (", currentEpisode=").append (currentEpisode);
        sb.append (", appUser=").append (appUser.toString ());
        sb.append (", serie=").append (serie.toString ());
        sb.append (", datelastAction=").append (dateLastAction);
        sb.append ('}');
        return sb.toString ();
    }


    public ViewingSerie() {
        //default constructor
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

    public LocalDate getDateLastAction() {
        return dateLastAction;
    }

    public void setDateLastAction(LocalDate dateLastAction) {
        this.dateLastAction = dateLastAction;
    }

    public static final class Builder {

        private Status status;
        private Integer currentSeason;
        private Integer currentEpisode;
        private AppUser appUser;
        private Serie serie;
        private LocalDate dateLastAction;

        private Builder() {
        }

        public static Builder aViewingSerie() {
            return new Builder();
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

        public Builder withAppUser(AppUser appUser) {
            this.appUser = appUser;
            return this;
        }

        public Builder withSerie(Serie serie) {
            this.serie = serie;
            return this;
        }

        public Builder withDateLastAction(LocalDate date) {
            this.dateLastAction = date;
            return this;
        }

        public ViewingSerie build() {
            ViewingSerie viewingSerie = new ViewingSerie();
            viewingSerie.setStatus(status);
            viewingSerie.setCurrentSeason(currentSeason);
            viewingSerie.setCurrentEpisode(currentEpisode);
            viewingSerie.setAppUser(appUser);
            viewingSerie.setSerie(serie);
            viewingSerie.setDateLastAction (dateLastAction);
            return viewingSerie;
        }
    }
}
