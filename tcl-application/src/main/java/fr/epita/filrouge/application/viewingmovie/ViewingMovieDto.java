package fr.epita.filrouge.application.viewingmovie;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.epita.filrouge.application.movie.MovieDto;
import fr.epita.filrouge.application.person.AppUserDto;
import fr.epita.filrouge.domain.entity.common.Status;

public class ViewingMovieDto {

    @JsonProperty("status")
    private Status status;

    @JsonProperty("appUser")
    private AppUserDto appUserDto;

    @JsonProperty("movie")
    private MovieDto movieDto;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public AppUserDto getAppUserDto() {
        return appUserDto;
    }

    public void setAppUserDto(AppUserDto appUserDto) {
        this.appUserDto = appUserDto;
    }

    public MovieDto getMovieDto() {
        return movieDto;
    }

    public void setMovieDto(MovieDto movieDto) {
        this.movieDto = movieDto;
    }


    public static final class Builder {
        private Status status;
        private AppUserDto appUserDto;
        private MovieDto movieDto;

        private Builder() {
        }

        public static Builder aViewingMovieDto() {
            return new Builder();
        }

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder withAppUserDto(AppUserDto appUserDto) {
            this.appUserDto = appUserDto;
            return this;
        }

        public Builder withMovieDto(MovieDto movieDto) {
            this.movieDto = movieDto;
            return this;
        }

        public ViewingMovieDto build() {
            ViewingMovieDto viewingMovieDto = new ViewingMovieDto();
            viewingMovieDto.setStatus(status);
            viewingMovieDto.setAppUserDto(appUserDto);
            viewingMovieDto.setMovieDto(movieDto);
            return viewingMovieDto;
        }
    }
}
