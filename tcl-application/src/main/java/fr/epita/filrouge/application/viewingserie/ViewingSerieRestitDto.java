package fr.epita.filrouge.application.viewingserie;

import fr.epita.filrouge.application.serie.SerieDto;
import fr.epita.filrouge.domain.entity.common.Status;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ViewingSerieRestitDto {

    @Email
    private String email;
    @NotNull
    private Status status;
    @NotNull
    private Integer currentSeason;
    @NotNull
    private Integer currentEpisode;

    private SerieDto serieDto;

    private LocalDate dateLastAction;

    private Boolean alreadyInUserList;
    private Boolean love;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public SerieDto getSerieDto() {
        return serieDto;
    }

    public void setSerieDto(SerieDto serieDto) {
        this.serieDto = serieDto;
    }

    public LocalDate getDateLastAction() {
        return dateLastAction;
    }

    public void setDateLastAction(LocalDate dateLastAction) {
        this.dateLastAction = dateLastAction;
    }

    public Boolean getAlreadyInUserList() {
        return alreadyInUserList;
    }

    public void setAlreadyInUserList(Boolean alreadyInUserList) {
        this.alreadyInUserList = alreadyInUserList;
    }

    public Boolean getLove() {
        return love;
    }

    public void setLove(Boolean love) {
        this.love = love;
    }

    public static final class Builder {
        private String email;
        private Status status;
        private Integer currentSeason;
        private Integer currentEpisode;
        private SerieDto serieDto;
        private LocalDate dateLastAction;
        private Boolean alreadyInUserList;
        private Boolean love;

        private Builder() {
        }

        public static Builder aViewingSerieRestitDto() {
            return new Builder();
        }

        public Builder withEmail(String email) {
            this.email = email;
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

        public Builder withSerieDto(SerieDto serieDto) {
            this.serieDto = serieDto;
            return this;
        }

        public Builder withDateLastAction(LocalDate dateLastAction) {
            this.dateLastAction = dateLastAction;
            return this;
        }

        public Builder withAlreadyInUserList(Boolean alreadyInUserList) {
            this.alreadyInUserList = alreadyInUserList;
            return this;
        }

        public Builder withLove(Boolean love) {
            this.love = love;
            return this;
        }

        public ViewingSerieRestitDto build() {
            ViewingSerieRestitDto viewingSerieRestitDto = new ViewingSerieRestitDto();
            viewingSerieRestitDto.setEmail(email);
            viewingSerieRestitDto.setStatus(status);
            viewingSerieRestitDto.setCurrentSeason(currentSeason);
            viewingSerieRestitDto.setCurrentEpisode(currentEpisode);
            viewingSerieRestitDto.setSerieDto(serieDto);
            viewingSerieRestitDto.setDateLastAction(dateLastAction);
            viewingSerieRestitDto.setAlreadyInUserList(alreadyInUserList);
            viewingSerieRestitDto.setLove(love);
            return viewingSerieRestitDto;
        }
    }
}
