package fr.epita.filrouge.domain.video;

public class Episode extends Video {

    private Integer episodeNumber;

    //default constructor
    Episode() {
    }

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }


    public static final class Builder {
        private Integer episodeNumber;
        private Long id;
        private String title;
        private String description;
        private String imageUrl;
        private Double averageRating;
        private Integer numberOfVotes;

        private Builder() {
        }

        public static Builder anEpisode() {
            return new Builder();
        }

        public Builder withEpisodeNumber(Integer episodeNumber) {
            this.episodeNumber = episodeNumber;
            return this;
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder withAverageRating(Double averageRating) {
            this.averageRating = averageRating;
            return this;
        }

        public Builder withNumberOfVotes(Integer numberOfVotes) {
            this.numberOfVotes = numberOfVotes;
            return this;
        }

        public Episode build() {
            Episode episode = new Episode();
            episode.setEpisodeNumber(episodeNumber);
            episode.setId(id);
            episode.setTitle(title);
            episode.setDescription(description);
            episode.setImageUrl(imageUrl);
            episode.setAverageRating(averageRating);
            episode.setNumberOfVotes(numberOfVotes);
            return episode;
        }
    }
}
