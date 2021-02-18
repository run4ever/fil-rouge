package fr.epita.filrouge.infrastructure;

import fr.epita.filrouge.domain.person.UserPlaylist;
import fr.epita.filrouge.domain.person.UserPlaylistRepository;
import fr.epita.filrouge.infrastructure.UserPlaylistDAO;
import fr.epita.filrouge.infrastructure.playlist.UserPlaylistJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserPlaylistRepositoryImpl implements UserPlaylistRepository {

    @Autowired
    private UserPlaylistDAO userPlaylistDAO;


    @Override
    public void save(UserPlaylist userPlaylist) {
        userPlaylistDAO.save(new UserPlaylistJPA(userPlaylist));

    }

    @Override
    public UserPlaylist findById(Long id) throws Exception {
        return userPlaylistDAO.findById(id).map(UserPlaylistJPA::toUserPlaylist).orElseThrow(()-> new Exception());
    }

}
