package fr.epita.filrouge.infrastructure;

import fr.epita.filrouge.infrastructure.playlist.UserPlaylistJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPlaylistDAO extends JpaRepository<UserPlaylistJPA, Long> {

}
