package fr.epita.filrouge.infrastructure.playlist;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPlaylistDAO extends JpaRepository<UserPlaylistJPA, Long> {
}
