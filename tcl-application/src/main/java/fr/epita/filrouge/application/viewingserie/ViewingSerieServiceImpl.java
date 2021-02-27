package fr.epita.filrouge.application.viewingserie;

import fr.epita.filrouge.application.common.PageDTO;
import fr.epita.filrouge.application.mapper.AppUserDtoMapper;
import fr.epita.filrouge.application.mapper.MapperSerieDto;
import fr.epita.filrouge.domain.entity.person.AppUserRepository;
import fr.epita.filrouge.domain.entity.serie.SerieRepository;
import fr.epita.filrouge.domain.entity.viewingserie.ViewingSerie;
import fr.epita.filrouge.domain.entity.viewingserie.ViewingSerieRepository;
import fr.epita.filrouge.domain.exception.AlreadyExistingException;
import fr.epita.filrouge.domain.exception.ErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Yoss
 * Classe pour manipuler les visionnages de série
 */
@Service
@Transactional
public class ViewingSerieServiceImpl implements ViewingSerieService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private SerieRepository serieRepository;

    @Autowired
    private ViewingSerieRepository viewingSerieRepository;



    @Autowired
    private MapperSerieDto mapperSerieDto;

    @Autowired
    private AppUserDtoMapper appUserDtoMapper;

    /**
     * Création d'un nouveau visionnage
     * @param serieViewDto
     */
    @Override
    public void create(ViewingSerieCreateDto serieViewDto) {

        if (viewingSerieRepository.findByIdUserAndIdSerie (serieViewDto.getEmail (), serieViewDto.getImdb ()) != null) {
            throw new AlreadyExistingException ("ViewingSerie existing : Id imdb : " + serieViewDto.getImdb ()
                    + " user : " + serieViewDto.getEmail (), ErrorCodes.VIEWVING_SERIE_ALREADY_EXISTING);
        }

        ViewingSerie viewingSerie = new ViewingSerie ();
        viewingSerie.setCurrentEpisode (serieViewDto.getCurrentEpisode ());
        viewingSerie.setCurrentSeason (serieViewDto.getCurrentSeason ());
        viewingSerie.setStatus (serieViewDto.getStatus ());
        viewingSerie.setAppUser (appUserRepository.findbyEmail (serieViewDto.getEmail ()));
        viewingSerie.setSerie (serieRepository.findById (serieViewDto.getImdb ()));

        viewingSerieRepository.create (viewingSerie);

    }

    /**
     *
     * Récupération de la liste des visionnages pour un user donné.
     * Méthode sans pagination.
     * @param email
     * @return liste de visionnage
     */
    @Override
    public List<ViewingSerieRestitDto> findByUserAllVievingSerieDto(String email) {

        List<ViewingSerieRestitDto> viewingSerieRestitDtos = new ArrayList<ViewingSerieRestitDto> ();
        for (ViewingSerie viewingSerie : viewingSerieRepository.findallViewingSerieByUser(email)) {
            ViewingSerieRestitDto viewingSerieRestitDto = new ViewingSerieRestitDto ();
            viewingSerieRestitDto.setCurrentEpisode (viewingSerie.getCurrentEpisode ());
            viewingSerieRestitDto.setCurrentSeason (viewingSerie.getCurrentSeason ());
            viewingSerieRestitDto.setEmail (viewingSerie.getAppUser ().getEmail ());
            viewingSerieRestitDto.setSerieDto (mapperSerieDto.mapDomainToDto (viewingSerie.getSerie ()));
            viewingSerieRestitDto.setStatus (viewingSerie.getStatus ());
            viewingSerieRestitDtos.add (viewingSerieRestitDto);
        }
        return viewingSerieRestitDtos;
    }

    /**
     * Récupération de la liste des visionnages pour un user donné.
     * Méthode avec pagination.
     * @param email
     * @param offset
     * @param limit
     * @return
     */

    @Override
    public PageDTO findByUserAllVievingSerieDtoByPage(String email, int offset, int limit, String sortAttribute, boolean sortAsc) {

        PageDTO pageDTO = new PageDTO ();
        List<ViewingSerieRestitDto> viewingSerieRestitDtos = new ArrayList<ViewingSerieRestitDto> ();
        String attributeVerify = "";
        if (controlSortAttribute (sortAttribute)) {
            attributeVerify.concat (sortAttribute);
        }
        else {
            attributeVerify.concat("DateLastAction");
        }
        for (ViewingSerie viewingSerie : viewingSerieRepository.findallViewingSerieByUserByPage(email, offset, limit, attributeVerify, sortAsc)) {
            ViewingSerieRestitDto viewingSerieRestitDto = new ViewingSerieRestitDto ();
            viewingSerieRestitDto.setCurrentEpisode (viewingSerie.getCurrentEpisode ());
            viewingSerieRestitDto.setCurrentSeason (viewingSerie.getCurrentSeason ());
            viewingSerieRestitDto.setEmail (viewingSerie.getAppUser ().getEmail ());
            viewingSerieRestitDto.setSerieDto (mapperSerieDto.mapDomainToDto (viewingSerie.getSerie ()));
            viewingSerieRestitDto.setStatus (viewingSerie.getStatus ());
            viewingSerieRestitDtos.add (viewingSerieRestitDto);
        }
        pageDTO.setLimit (limit);
        pageDTO.setOffset (offset);
        pageDTO.setSortAttribute (sortAttribute);
        pageDTO.setTotal (viewingSerieRepository.countTotalViewingSerieByUser(email));
        pageDTO.setListViewingOrSerieOrVideo (viewingSerieRestitDtos);
        return pageDTO;
    }

    /**
     * Affichage d'un visionnage donné
     * @param idUser
     * @param imdb
     * @return
     */
    @Override
    public ViewingSerieRestitDto findViewing(String idUser, String imdb) {

        ViewingSerieRestitDto viewingSerieRestitDto = new ViewingSerieRestitDto ();
        ViewingSerie viewingSerie = viewingSerieRepository.findByIdUserAndIdSerie (idUser, imdb);
        viewingSerieRestitDto.setCurrentEpisode (viewingSerie.getCurrentEpisode ());
        viewingSerieRestitDto.setCurrentSeason (viewingSerie.getCurrentSeason ());
        viewingSerieRestitDto.setEmail (viewingSerie.getAppUser ().getEmail ());
        viewingSerieRestitDto.setSerieDto (mapperSerieDto.mapDomainToDto (viewingSerie.getSerie ()));
        viewingSerieRestitDto.setStatus (viewingSerie.getStatus ());

        return viewingSerieRestitDto;

    }



    /**
     * Controle par introspection d'un critère de tri.
     * La méthode vérifie la présence de l'attribut
     * @param attribute
     * @return
     */
    private boolean controlSortAttribute(String attribute) {

        for (Field field: ViewingSerie.class.getFields ()) {
            if (field.getName ().equalsIgnoreCase (attribute)) {
                return true;
            }
        }
        return false;
    }
}