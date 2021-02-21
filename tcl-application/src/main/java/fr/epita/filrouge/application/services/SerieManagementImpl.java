package fr.epita.filrouge.application.services;

import fr.epita.filrouge.application.dto.serie.SerieDTO;
import fr.epita.filrouge.application.mapper.serie.MapperSerieDto;
import fr.epita.filrouge.domain.entity.serie.ISerie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Liste des services applicatifs de l'objet Serie
 */
@Service
@Transactional
public class SerieManagementImpl implements ISerieManagement{

    @Autowired
    ISerie iSerie;

    @Autowired
    MapperSerieDto mapperSerieDto;

    @Override
    public SerieDTO getSerieById(String id) {
        return mapperSerieDto.mapDomainToDto (iSerie.findById(id));
    }

    @Override
    public SerieDTO createSerie(SerieDTO serieDTO) {
        return mapperSerieDto.mapDomainToDto (iSerie.create(mapperSerieDto.mapDtoToDomain (serieDTO)));
    }
/*
    @Override
    public create(SerieDTO serie) {
    SerieDTO serieDTO =iSerie.create (mapperSerieDto.mapDtoToDomain (serie));
    return serieDTO.getImdbId ();

    }
*/

}
