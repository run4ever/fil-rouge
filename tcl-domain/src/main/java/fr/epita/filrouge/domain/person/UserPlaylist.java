package fr.epita.filrouge.domain.person;

import fr.epita.filrouge.domain.video.Video;


public class UserPlaylist {

    private Long id;
    private User user;
    private Video video;
    private Integer notationUser;
    private Status status;


    //default constructor visibility package
    UserPlaylist() {   }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Integer getNotationUser() {
        return notationUser;
    }

    public void setNotationUser(Integer notationUser) {
        this.notationUser = notationUser;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public static final class Builder {
        private Long id;
        private User user;
        private Video video;
        private Integer notationUser;
        private Status status;

        private Builder() {
        }

        public static Builder anUserPlaylist() {
            return new Builder();
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public Builder withVideo(Video video) {
            this.video = video;
            return this;
        }

        public Builder withNotationUser(Integer notationUser) {
            this.notationUser = notationUser;
            return this;
        }

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public UserPlaylist build() {
            UserPlaylist userPlaylist = new UserPlaylist();
            userPlaylist.setId(id);
            userPlaylist.setUser(user);
            userPlaylist.setVideo(video);
            userPlaylist.setNotationUser(notationUser);
            userPlaylist.setStatus(status);
            return userPlaylist;
        }
    }
}
