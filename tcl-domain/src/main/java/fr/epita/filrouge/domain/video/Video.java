package fr.epita.filrouge.domain.video;

import java.util.List;

public class Video {

    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private Double averageRating;
    private Integer numberOfVotes;
    private Category category;
    private List<Actor> actors;

    //default constructor visibility package
    Video() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }


    public static final class Builder {
        private Long id;
        private String title;
        private String description;
        private String imageUrl;
        private Double averageRating;
        private Integer numberOfVotes;
        private Category category;
        private List<Actor> actors;

        private Builder() {
        }

        public static Builder aVideo() {
            return new Builder();
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

        public Builder withCategory(Category category) {
            this.category = category;
            return this;
        }

        public Builder withActors(List<Actor> actors) {
            this.actors = actors;
            return this;
        }

        public Video build() {
            Video video = new Video();
            video.setId(id);
            video.setTitle(title);
            video.setDescription(description);
            video.setImageUrl(imageUrl);
            video.setAverageRating(averageRating);
            video.setNumberOfVotes(numberOfVotes);
            video.setCategory(category);
            video.setActors(actors);
            return video;
        }
    }
}
