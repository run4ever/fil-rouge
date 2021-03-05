package fr.epita.filrouge.infrastructure.http;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieInfo {

    // conversion tool : https://json2csharp.com/json-to-pojo

    @JsonProperty("Title")
    public String title;
    @JsonProperty("Year")
    public String year;
    @JsonProperty("Released")
    public String releaseDate;
    @JsonProperty("Runtime")
    public String duration;
    @JsonProperty("Genre")
    public String category;
    @JsonProperty("Director")
    public String director;
    @JsonProperty("Writer")
    public String writer;
    @JsonProperty("Actors")
    public String actors;
    @JsonProperty("Plot")
    public String description;
    @JsonProperty("Poster")
    public String imageUrl;
    public String imdbRating;
    public String imdbVotes;
    public String imdbID;
    @JsonProperty("Type")
    public String type;

    public MovieInfo() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
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

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MovieInfo{");
        sb.append("title='").append(title).append('\'');
        sb.append(", year='").append(year).append('\'');
        sb.append(", releaseDate='").append(releaseDate).append('\'');
        sb.append(", duration='").append(duration).append('\'');
        sb.append(", category='").append(category).append('\'');
        sb.append(", director='").append(director).append('\'');
        sb.append(", writer='").append(writer).append('\'');
        sb.append(", actors='").append(actors).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", imageUrl='").append(imageUrl).append('\'');
        sb.append(", imdbRating='").append(imdbRating).append('\'');
        sb.append(", imdbVotes='").append(imdbVotes).append('\'');
        sb.append(", imdbID='").append(imdbID).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
