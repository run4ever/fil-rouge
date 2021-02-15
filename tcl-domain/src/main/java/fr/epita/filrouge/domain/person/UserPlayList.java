package fr.epita.filrouge.domain.person;

import java.util.ArrayList;
import java.util.List;

public class UserPlayList {

    private Long id;

    private User user;

    private List<UserPlayListItem> userPlayListItems = new ArrayList<>();;

   //default constructor
    UserPlayList() {   }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UserPlayListItem> getUserPlayListItems() {
        return userPlayListItems;
    }

    public void setUserPlayListItems(List<UserPlayListItem> userPlayListItems) {
        this.userPlayListItems = userPlayListItems;
    }


    public static final class Builder {
        private Long id;
        private User user;
        private List<UserPlayListItem> userPlayListItems;

        private Builder() {
        }

        public static Builder anUserPlayList() {
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

        public Builder withUserPlayListItems(List<UserPlayListItem> userPlayListItems) {
            this.userPlayListItems = userPlayListItems;
            return this;
        }

        public UserPlayList build() {
            UserPlayList userPlayList = new UserPlayList();
            userPlayList.setUser(user);
            userPlayList.setUserPlayListItems(userPlayListItems);
            return userPlayList;
        }
    }
}
