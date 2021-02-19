package fr.epita.filrouge.domain.entity.common;

public class PublicNotation {
    private Double averageRating;
    private Integer numberOfVotes;

    public PublicNotation(Double averageRating, Integer numberOfVotes) {
        this.averageRating = averageRating;
        this.numberOfVotes = numberOfVotes;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(Integer numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }
}
