package fr.epita.filrouge.infrastructure.video;



import fr.epita.filrouge.infrastructure.person.ActorJPA;
import fr.epita.filrouge.domain.video.Category;
import javax.persistence.*;
import java.util.List;

@Entity(name = "video")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class VideoJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;

    private String description;

    private String imageUrl;

    private Double averageRating;

    private Integer numberOfVotes;

    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToMany
    private List<ActorJPA> actors;

    public VideoJPA() {
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

    public List<ActorJPA> getActors() {
        return actors;
    }

    public void setActors(List<ActorJPA> actors) {
        this.actors = actors;
    }
}
