package fr.epita.filrouge.infrastructure.playlist;

import fr.epita.filrouge.domain.person.Status;
import fr.epita.filrouge.infrastructure.person.AppUserJPA;
import fr.epita.filrouge.infrastructure.video.MovieJPA;


import javax.persistence.*;

//@Entity(name= "user_playlist_movie")
public class UserPlaylistMovieJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private AppUserJPA appUser;

    @OneToOne
    private MovieJPA movie;

    private Integer notationUser;

    private Status status;
}
