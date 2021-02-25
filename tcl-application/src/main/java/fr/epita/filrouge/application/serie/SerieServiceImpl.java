package fr.epita.filrouge.application.serie;

import fr.epita.filrouge.domain.entity.serie.Serie;
import fr.epita.filrouge.domain.entity.serie.SerieRepository;
import fr.epita.filrouge.domain.exception.AlreadyExistingException;
import fr.epita.filrouge.domain.exception.ErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SerieServiceImpl implements SerieService {


    @Autowired
    private SerieRepository serieRepository;


    @Override
    public Serie getSerieById(String imdbId) {
        if (imdbId == null) {
            return null;
        }
        return serieRepository.findById (imdbId);
    }

    @Override
    public void createSerie(Serie serie) {

        final Serie result = serieRepository.findById (serie.getImdbId());
        if (result != null) {

              throw new AlreadyExistingException("Serie Already exist" + serie.getTitle (),ErrorCodes.SERIE_NOT_FOUND);
        }
        else serieRepository.create (serie);
    }

}


