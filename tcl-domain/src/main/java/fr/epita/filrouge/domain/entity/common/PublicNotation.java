package fr.epita.filrouge.domain.entity.common;

public class PublicNotation {
    private Double averageRating;
    private Integer nombreOfVotes;

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getNombreOfVotes() {
        return nombreOfVotes;
    }

    public void setNombreOfVotes(Integer nombreOfVotes) {
        this.nombreOfVotes = nombreOfVotes;
    }

}
