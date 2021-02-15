package fr.epita.filrouge.domain.video;


import java.util.Date;
import java.util.List;

public class Serie extends Video {

    private Long id;
    private Date dateStart ;
    private List<Season> numberOfSeason;

    Serie() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public List<Season> getNumberOfSeason() {
        return numberOfSeason;
    }

    public void setNumberOfSeason(List<Season> numberOfSeason) {
        this.numberOfSeason = numberOfSeason;
    }


    /**
     *
     */
    public static final class Builder {
        private Long id;
        private Date dateStart ;
        private List<Season> numberOfSeason;

        private Builder() {
        }

        public static Builder aSerie() {
            return new Builder ();
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withDateStart(Date dateStart) {
            this.dateStart = dateStart;
            return this;
        }

        public Builder withNumberOfSeason(List<Season> numberOfSeason) {
            this.numberOfSeason = numberOfSeason;
            return this;
        }

        public Serie build () {
            Serie serie = new Serie ();
            serie.setId (id);
            serie.setDateStart (dateStart);
            serie.setNumberOfSeason (numberOfSeason);
            return serie;
        }
    }
}
