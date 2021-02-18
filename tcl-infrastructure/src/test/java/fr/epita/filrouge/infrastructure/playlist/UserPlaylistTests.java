package fr.epita.filrouge.infrastructure.playlist;


import fr.epita.filrouge.domain.person.AppUser;
import fr.epita.filrouge.domain.person.Role;
import fr.epita.filrouge.domain.person.UserPlaylist;
import fr.epita.filrouge.domain.person.UserPlaylistRepository;
import fr.epita.filrouge.domain.video.Category;
import fr.epita.filrouge.domain.video.Movie;
import fr.epita.filrouge.domain.video.Video;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase
public class UserPlaylistTests {

    @Autowired
    private UserPlaylistRepository userPlaylistRepository;


    @Test
    public void create_User_playlist_unique() throws Exception {
        //Given
        AppUser user = AppUser.Builder.anAppUser()
                .withId(12345678L).withFirstname("Jean").withLastname("DUPONT").withEmail("jean.dupont@gmail.com").withRole(Role.ROLE_USER)
                .build();

        Video movie = Movie.Builder.aMovie()
                .withId(111L).withTitle("Age de glace 1").withAverageRating(4.0).withNumberOfVotes(200).withCategory(Category.FANTASY)
                .withDescription("La glace va se fondre").withImageUrl("IMAGE_TO_FIND.JPG")
                .build();

        UserPlaylist userPlaylist = UserPlaylist.Builder.anUserPlaylist()
                .withUser(user)
                .withVideo(movie)
                .build();

        System.out.println(userPlaylist.toString());
        //When
        userPlaylistRepository.save(userPlaylist);

        //Then
        //UserPlaylist playlist = userPlaylistRepository.findById(111L);

    }
}
