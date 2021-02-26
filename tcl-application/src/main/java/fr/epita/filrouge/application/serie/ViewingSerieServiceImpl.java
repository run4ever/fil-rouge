package fr.epita.filrouge.application.serie;

import fr.epita.filrouge.domain.entity.serie.ViewingSerie;
import fr.epita.filrouge.domain.entity.serie.ViewingSerieRepository;
import fr.epita.filrouge.domain.exception.AlreadyExistingException;
import fr.epita.filrouge.domain.exception.ErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ViewingSerieServiceImpl implements ViewingSerieService{

    @Autowired
    private ViewingSerieRepository viewingSerieRepository;

    @Override
    public ViewingSerie findById(String id) {
        return viewingSerieRepository.findById (id);


    }

    @Override
    public void create(ViewingSerie serieView) {

        final ViewingSerie  result = viewingSerieRepository.findById(serieView.getAppUser ().getEmail ());
        if (result != null) {
            throw new AlreadyExistingException ("Viewving serie is already exists " + serieView.getAppUser ().getEmail (),ErrorCodes.VIEWVING_SERIE_ALREADY_EXISTING);
        }
        viewingSerieRepository.create (serieView);

    }

    @Override
    public List<ViewingSerie> findAllVievingSerie() {
        return viewingSerieRepository.findAllVievingSerie ();
    }

}
