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

    private String imdbId;
    private String email;
    @NotNull
    private Boolean likeOrNot = false;

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

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getLikeOrNot() {
        return likeOrNot;
    }

    public void setLikeOrNot(Boolean likeOrNot) {
        this.likeOrNot = likeOrNot;
    }

    public static final class Builder {
        private Status status;
        private Integer currentSeason;
        private Integer currentEpisode;
        private String imdbId;
        private String email;
        private Boolean likeOrNot = false;

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

        public Builder withImdbId(String imdbId) {
            this.imdbId = imdbId;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withLikeOrNot(Boolean likeOrNot) {
            this.likeOrNot = likeOrNot;
            return this;
        }

        public ViewingSerieCreateDto build() {
            ViewingSerieCreateDto viewingSerieCreateDto = new ViewingSerieCreateDto();
            viewingSerieCreateDto.setStatus(status);
            viewingSerieCreateDto.setCurrentSeason(currentSeason);
            viewingSerieCreateDto.setCurrentEpisode(currentEpisode);
            viewingSerieCreateDto.setImdbId(imdbId);
            viewingSerieCreateDto.setEmail(email);
            viewingSerieCreateDto.setLikeOrNot(likeOrNot);
            return viewingSerieCreateDto;
        }
    }
}
