package fr.epita.filrouge.domain.video;

import java.time.LocalDate;
import java.util.List;

public class Serie {

    private Long id;
    private LocalDate dateStart ;
    private List<Season> seasons;

    //default constructor visibility package
    Serie() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }


    public static final class Builder {
        private Long id;
        private LocalDate dateStart ;
        private List<Season> seasons;

        private Builder() {
        }

        public static Builder aSerie() {
            return new Builder();
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withDateStart(LocalDate dateStart) {
            this.dateStart = dateStart;
            return this;
        }

        public Builder withSeasons(List<Season> seasons) {
            this.seasons = seasons;
            return this;
        }

        public Serie build() {
            Serie serie = new Serie();
            serie.setId(id);
            serie.setDateStart(dateStart);
            serie.setSeasons(seasons);
            return serie;
        }
    }
}
