package fr.epita.filrouge.domain.person;

public interface UserPlaylistRepository {
    void save(UserPlaylist userPlaylist);

    UserPlaylist findById(Long id) throws Exception;
}
