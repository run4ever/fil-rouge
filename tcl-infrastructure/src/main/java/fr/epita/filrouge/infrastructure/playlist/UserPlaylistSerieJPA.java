package fr.epita.filrouge.infrastructure.playlist;


import fr.epita.filrouge.domain.person.Status;
import fr.epita.filrouge.infrastructure.person.AppUserJPA;
import fr.epita.filrouge.infrastructure.video.SerieJPA;

import javax.persistence.*;

//@Entity(name = "user_playlist_serie")
public class UserPlaylistSerieJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private AppUserJPA appUser;

    @OneToOne
    private SerieJPA serie;

    private Integer notationUser;

    private Status status;

    private Integer seasonNumber;

    private Integer episodeNumber;

}
