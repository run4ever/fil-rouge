package fr.epita.filrouge.application.movie;

import fr.epita.filrouge.domain.entity.common.Status;
import fr.epita.filrouge.domain.entity.movie.Movie;
import fr.epita.filrouge.domain.entity.movie.ViewingMovie;
import fr.epita.filrouge.domain.entity.movie.ViewingMovieRepository;
import fr.epita.filrouge.domain.entity.person.AppUser;
import fr.epita.filrouge.domain.exception.AlreadyExistingException;
import fr.epita.filrouge.domain.exception.ErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewingMovieServiceImpl implements ViewingMovieService{

    @Autowired
    private ViewingMovieRepository viewingMovieRepository;

    @Override
    public void addMovieToViewingMovie(AppUser appUser, Movie movie) {
        // si un AppUser a déjà le Movie dans son visionnage,
        // alors il n'est pas possible d'ajouter le Movie 2è fois dans ViewingMovie
        List<ViewingMovie> viewingMovie = viewingMovieRepository.findViewingMovieFromUser(appUser);

        if(viewingMovie!= null) {//si viewingMovie n'est pas null, il faut tester si on a le Movie déjà dans ViewingMovie
            Boolean movieInViewingMovie = false;

            for (ViewingMovie vm : viewingMovie) {
                if (vm.getMovie().getImdbId() == movie.getImdbId()) {
                    movieInViewingMovie = true; //on a déjà ce Movie dans ViewingMovie
                    break;// on sort de la boucle dès qu'on a trouvé une fois le Movie
                }
            }
            if (movieInViewingMovie) {
                //si Movie est déjà présent alors Exception
                throw new AlreadyExistingException("Movie already exiting in ViewingMovie " + movie.getImdbId() + " " + movie.getTitle(),
                        ErrorCodes.MOVIE_ALREADY_EXISTING_IN_VIEWINGMOVIE);
            }
        }

        //Movie n'est pas encore dans ViewingMovie donc on le crée
        ViewingMovie vm = ViewingMovie.Builder.aViewingMovie()
                .withStatus(Status.TO_WATCH) //à la création on met par défaut Movie à regarder
                .withAppUser(appUser)
                .withMovie(movie)
                .build();
        viewingMovieRepository.create(vm);
    }

    @Override
    public List<ViewingMovie> getViewingMovie(AppUser appUser) {
        if(appUser == null) {
            return null;
        }
        return viewingMovieRepository.findViewingMovieFromUser(appUser);
    }

}
