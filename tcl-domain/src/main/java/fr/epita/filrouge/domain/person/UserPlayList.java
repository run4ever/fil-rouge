package fr.epita.filrouge.domain.person;

import fr.epita.filrouge.domain.video.Video;


import java.util.List;

public class UserPlayList {
    private Long id;

    private int notationUser;

    private Status status;

    private List<Video> videoList;

   //constructeur par défaut pour Spring
    UserPlayList() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNotationUser() {
        return notationUser;
    }

    public void setNotationUser(int notationUser) {
        this.notationUser = notationUser;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Video> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }

    /** Début Builder */
    public static final class Builder {
        private Long id;
        private int notationUser;
        private Status status;
        private List<Video> videoList;

        private Builder() {
        }

        public static Builder anUserPlayList() {
            return new Builder();
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withNotationUser(int notationUser) {
            this.notationUser = notationUser;
            return this;
        }

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder withVideoList(List<Video> videoList) {
            this.videoList = videoList;
            return this;
        }

        public UserPlayList build() {
            UserPlayList userPlayList = new UserPlayList();
            userPlayList.setId(id);
            userPlayList.setNotationUser(notationUser);
            userPlayList.setStatus(status);
            userPlayList.setVideoList(videoList);
            return userPlayList;
        }
    }

    /** Fin Builder */

}
