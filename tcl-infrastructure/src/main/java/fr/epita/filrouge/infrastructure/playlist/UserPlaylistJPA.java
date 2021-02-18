package fr.epita.filrouge.infrastructure.playlist;

import fr.epita.filrouge.domain.person.Status;
import fr.epita.filrouge.domain.person.UserPlaylist;
import fr.epita.filrouge.infrastructure.person.AppUserJPA;
import fr.epita.filrouge.infrastructure.video.VideoJPA;

import javax.persistence.*;

@Entity(name="user_playlist")
public class UserPlaylistJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private AppUserJPA appUserJPA;

    @OneToOne
    private VideoJPA videoJPA;

    private Integer notationUser;

    private Status status;

    private Integer seasonNumber;

    private Integer episodeNumber;

    public UserPlaylistJPA() {
    }

    public UserPlaylistJPA(UserPlaylist userPlaylist) {
        this.id = userPlaylist.getId();
        this.appUserJPA = new AppUserJPA(userPlaylist.getUser());
        //this.videoJPA = new VideoJPA(userPlaylist.getVideo());
        this.notationUser = userPlaylist.getNotationUser();
        this.status = userPlaylist.getStatus();
        this.seasonNumber = userPlaylist.getSeasonNumber();
        this.episodeNumber = userPlaylist.getEpisodeNumber();
    }

   public UserPlaylist toUserPlaylist() {
        //convertir UserPlaylistJPA To UserPlaylist
       return UserPlaylist.Builder.anUserPlaylist()
               .withId(this.id)
               .withSeasonNumber(this.seasonNumber)
               .withEpisodeNumber(this.episodeNumber)
               //les autres champs à jouter
               .withStatus(this.status)
               .build();
   }
}
