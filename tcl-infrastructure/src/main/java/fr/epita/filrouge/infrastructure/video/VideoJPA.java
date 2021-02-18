package fr.epita.filrouge.infrastructure.video;


import fr.epita.filrouge.domain.video.Category;
import fr.epita.filrouge.domain.video.Video;
import fr.epita.filrouge.domain.video.VideoType;
import fr.epita.filrouge.infrastructure.person.ActorJPA;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "video")
public class VideoJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String imageUrl;

    private Double averageRating;

    private Integer numberOfVotes;

    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Integer numberIfSeason;

    private Integer numberOfEpisode;

    @Enumerated(EnumType.STRING)
    private VideoType videoType;

    @OneToMany
    private List<ActorJPA> actors;


    public VideoJPA() {
    }

    public VideoJPA(Video video) {
        this.id = video.getId();
        this.title = video.getTitle();
        this.description = video.getDescription();
        this.imageUrl = video.getImageUrl();
        this.averageRating = video.getAverageRating();
        this.numberOfVotes = video.getNumberOfVotes();
      //  this.releaseDate = ;   // releaseDate à ajouter dans domaine
        //this.category = category;
        //this.numberIfSeason = numberIfSeason;
        //this.numberOfEpisode = numberOfEpisode;
        //this.videoType = videoType;
        this.actors = video.getActors().stream().map(ActorJPA::new).collect(Collectors.toList());
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getNumberIfSeason() {
        return numberIfSeason;
    }

    public void setNumberIfSeason(Integer numberIfSeason) {
        this.numberIfSeason = numberIfSeason;
    }

    public Integer getNumberOfEpisode() {
        return numberOfEpisode;
    }

    public void setNumberOfEpisode(Integer numberOfEpisode) {
        this.numberOfEpisode = numberOfEpisode;
    }

    public VideoType getVideoType() {
        return videoType;
    }

    public void setVideoType(VideoType videoType) {
        this.videoType = videoType;
    }

    public List<ActorJPA> getActors() {
        return actors;
    }

    public void setActors(List<ActorJPA> actors) {
        this.actors = actors;
    }


    public static final class Builder {
        private Long id;
        private String title;
        private String description;
        private String imageUrl;
        private Double averageRating;
        private Integer numberOfVotes;
        private LocalDate releaseDate;
        private Category category;
        private Integer numberIfSeason;
        private Integer numberOfEpisode;
        private VideoType videoType;
        private List<ActorJPA> actors;

        private Builder() {
        }

        public static Builder aVideoJPA() {
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

        public Builder withReleaseDate(LocalDate releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public Builder withCategory(Category category) {
            this.category = category;
            return this;
        }

        public Builder withNumberIfSeason(Integer numberIfSeason) {
            this.numberIfSeason = numberIfSeason;
            return this;
        }

        public Builder withNumberOfEpisode(Integer numberOfEpisode) {
            this.numberOfEpisode = numberOfEpisode;
            return this;
        }

        public Builder withVideoType(VideoType videoType) {
            this.videoType = videoType;
            return this;
        }

        public Builder withActors(List<ActorJPA> actors) {
            this.actors = actors;
            return this;
        }

        public VideoJPA build() {
            VideoJPA videoJPA = new VideoJPA();
            videoJPA.setId(id);
            videoJPA.setTitle(title);
            videoJPA.setDescription(description);
            videoJPA.setImageUrl(imageUrl);
            videoJPA.setAverageRating(averageRating);
            videoJPA.setNumberOfVotes(numberOfVotes);
            videoJPA.setReleaseDate(releaseDate);
            videoJPA.setCategory(category);
            videoJPA.setNumberIfSeason(numberIfSeason);
            videoJPA.setNumberOfEpisode(numberOfEpisode);
            videoJPA.setVideoType(videoType);
            videoJPA.setActors(actors);
            return videoJPA;
        }
    }
}
