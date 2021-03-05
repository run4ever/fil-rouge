package fr.epita.filrouge.application.viewingserie;

import fr.epita.filrouge.domain.entity.common.Status;

import javax.validation.constraints.NotNull;

public class ViewingSerieCreateDto {

    @NotNull
    private Status status;
    @NotNull
    private Integer currentSeason;
    @NotNull
    private Integer currentEpisode;

    private String imdb;
    private String email;

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

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public static final class Builder {
        private Status status;
        private Integer currentSeason;
        private Integer currentEpisode;
        private String imdb;
        private String email;

        private Builder() {
        }

        public static Builder aViewingSerieCreateDto() {
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

        public Builder withImdb(String imdb) {
            this.imdb = imdb;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public ViewingSerieCreateDto build() {
            ViewingSerieCreateDto viewingSerieCreateDto = new ViewingSerieCreateDto();
            viewingSerieCreateDto.setStatus(status);
            viewingSerieCreateDto.setCurrentSeason(currentSeason);
            viewingSerieCreateDto.setCurrentEpisode(currentEpisode);
            viewingSerieCreateDto.setImdb(imdb);
            viewingSerieCreateDto.setEmail(email);
            return viewingSerieCreateDto;
        }
    }
}
