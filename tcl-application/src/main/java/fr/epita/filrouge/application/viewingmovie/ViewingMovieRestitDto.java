package fr.epita.filrouge.application.viewingmovie;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.epita.filrouge.application.movie.MovieDto;
import fr.epita.filrouge.domain.entity.common.Status;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ViewingMovieRestitDto {

    @Email
    private String email;

    @JsonProperty("status")
    @NotNull
    private Status status;

    @JsonProperty("movieDto")
    private MovieDto movieDto;

    private LocalDate dateLastAction;

    private Boolean alReadyInUserList;

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

    public MovieDto getMovieDto() {
        return movieDto;
    }

    public void setMovieDto(MovieDto movieDto) {
        this.movieDto = movieDto;
    }

    public LocalDate getDateLastAction() {
        return dateLastAction;
    }

    public void setDateLastAction(LocalDate dateLastAction) {
        this.dateLastAction = dateLastAction;
    }

    public Boolean getAlReadyInUserList() {
        return alReadyInUserList;
    }

    public void setAlReadyInUserList(Boolean alReadyInUserList) {
        this.alReadyInUserList = alReadyInUserList;
    }

    public static final class Builder {
        private String email;
        private Status status;
        private MovieDto movieDto;
        private LocalDate dateLastAction;
        private Boolean alReadyInUserList = false;

        private Builder() {
        }

        public static Builder aViewingMovieRestitDto() {
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

        public Builder withMovieDto(MovieDto movieDto) {
            this.movieDto = movieDto;
            return this;
        }

        public Builder withDateLastAction(LocalDate dateLastAction) {
            this.dateLastAction = dateLastAction;
            return this;
        }

        public Builder withAlReadyInUserList(Boolean alReadyInUserList) {
            this.alReadyInUserList = alReadyInUserList;
            return this;
        }

        public ViewingMovieRestitDto build() {
            ViewingMovieRestitDto viewingMovieRestitDto = new ViewingMovieRestitDto();
            viewingMovieRestitDto.setEmail(email);
            viewingMovieRestitDto.setStatus(status);
            viewingMovieRestitDto.setMovieDto(movieDto);
            viewingMovieRestitDto.setDateLastAction(dateLastAction);
            viewingMovieRestitDto.setAlReadyInUserList(alReadyInUserList);
            return viewingMovieRestitDto;
        }
    }
}
