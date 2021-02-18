package fr.epita.filrouge.infrastructure.playlist;

import fr.epita.filrouge.domain.person.UserPlaylist;

public interface UserPlaylistRepository {
    void save(UserPlaylist userPlaylist);

    UserPlaylist findById(Long id) throws Exception;
}
