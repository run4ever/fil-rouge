package fr.epita.filrouge.application.serie;

import fr.epita.filrouge.application.mapper.MapperSerieDto;
import fr.epita.filrouge.domain.entity.serie.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SerieServiceImpl implements SerieService {


    @Autowired
    SerieRepository serieRepository;

    @Autowired
    MapperSerieDto mapperSerieDto;


    @Override
    public SerieDto getSerieById(String id) {

        return mapperSerieDto.mapDomainToDto (serieRepository.findById (id));
    }

    @Override
    public SerieDto createSerie(SerieDto serieDto) {

        return mapperSerieDto.mapDomainToDto (serieRepository.create (mapperSerieDto.mapDtoToDomain (serieDto)));
    }
}





