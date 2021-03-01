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
}
