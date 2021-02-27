package fr.epita.filrouge.application.serie;

import fr.epita.filrouge.application.mapper.MapperSerieDto;
import fr.epita.filrouge.domain.entity.serie.SerieRepository;
import fr.epita.filrouge.domain.exception.AlreadyExistingException;
import fr.epita.filrouge.domain.exception.ErrorCodes;
import fr.epita.filrouge.domain.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SerieServiceImpl implements SerieService {

    private static Logger logger = LoggerFactory.getLogger(SerieServiceImpl.class);

    @Autowired
    SerieRepository serieRepository;

    @Autowired
    MapperSerieDto mapperSerieDto;


    @Override
    public SerieDto getSerieById(String id) {

        if (serieRepository.findById (id) == null) {
            throw new NotFoundException ("Serie not existing : " + id, ErrorCodes.SERIE_NOT_FOUND);
        }
        return mapperSerieDto.mapDomainToDto (serieRepository.findById (id));
    }

    @Override
    public SerieDto createSerie(SerieDto serieDto) {

        if (serieRepository.findById (serieDto.getImdbId ()) != null) {
            throw new AlreadyExistingException ("Serie existing : " + serieDto.getImdbId (), ErrorCodes.SERIE_ALREADY_EXISTING);
        }
        return mapperSerieDto.mapDomainToDto (serieRepository.create (mapperSerieDto.mapDtoToDomain (serieDto)));
    }

    @Override
    public boolean deleteSerie(String id) {
        if(serieRepository.findById (id) == null) {
            throw new NotFoundException ("Serie not existing : " + id, ErrorCodes.SERIE_NOT_FOUND);
        }
        return serieRepository.deleteSerie (id);
    }
}





