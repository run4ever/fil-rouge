package fr.epita.filrouge.application.viewingmovie;

import fr.epita.filrouge.domain.entity.common.Status;

import javax.validation.constraints.NotNull;

public class ViewingMovieCreateDto {

    @NotNull
    private Status status = Status.TO_WATCH;
    @NotNull
    private String imdbId;
    @NotNull
    private String email;
    @NotNull
    private Boolean love = false;

    public ViewingMovieCreateDto() {
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public Boolean getLove() {return love;}

    public void setLove(Boolean love) {this.love = love;}

    public static final class Builder {
        private Status status = Status.TO_WATCH;
        private String imdbId;
        private String email;
        private Boolean love = false;

        private Builder() {
        }

        public static Builder aViewingMovieCreateDto() {
            return new Builder();
        }

        public Builder withStatus(Status status) {
            this.status = status;
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

        public Builder withLove(Boolean love) {
            this.love = love;
            return this;
        }

        public ViewingMovieCreateDto build() {
            ViewingMovieCreateDto viewingMovieCreateDto = new ViewingMovieCreateDto();
            viewingMovieCreateDto.setStatus(status);
            viewingMovieCreateDto.setImdbId(imdbId);
            viewingMovieCreateDto.setEmail(email);
            viewingMovieCreateDto.setLove(love);
            return viewingMovieCreateDto;
        }
    }
}
