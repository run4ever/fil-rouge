package fr.epita.filrouge.application.viewingserie;

import fr.epita.filrouge.application.common.PageDTO;
import fr.epita.filrouge.application.mapper.MapperSerieDto;
import fr.epita.filrouge.domain.entity.person.AppUserRepository;
import fr.epita.filrouge.domain.entity.serie.SerieRepository;
import fr.epita.filrouge.domain.entity.viewingserie.ViewingSerie;
import fr.epita.filrouge.domain.entity.viewingserie.ViewingSerieRepository;
import fr.epita.filrouge.domain.exception.AlreadyExistingException;
import fr.epita.filrouge.domain.exception.ErrorCodes;
import fr.epita.filrouge.domain.exception.NotFoundException;
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


    /**
     * Création d'un nouveau visionnage
     * @param serieViewDto
     */
    @Override
    public ViewingSerieCreateDto create(ViewingSerieCreateDto serieViewDto) {

        if (viewingSerieRepository.findByIdUserAndIdSerie (serieViewDto.getEmail (), serieViewDto.getImdb ()) != null) {
            throw new AlreadyExistingException ("ViewingSerie existing : Id imdb : " + serieViewDto.getImdb ()
                    + " user : " + serieViewDto.getEmail (), ErrorCodes.VIEWVING_SERIE_ALREADY_EXISTING);
        }
        return mapToDtoCreate (viewingSerieRepository.create (mapToDomainCreate (serieViewDto)));

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

        if (appUserRepository.findbyEmail (email) == null) {
            throw new NotFoundException ("User inconnu : " + email, ErrorCodes.USER_NOT_FOUND);
        }
        List<ViewingSerieRestitDto> viewingSerieRestitDtos = new ArrayList<ViewingSerieRestitDto> ();
        for (ViewingSerie viewingSerie : viewingSerieRepository.findallViewingSerieByUser(email)) {
            viewingSerieRestitDtos.add (mapToDtoRestit (viewingSerie));
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
            viewingSerieRestitDtos.add (mapToDtoRestit (viewingSerie));
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

        return mapToDtoRestit (viewingSerieRepository.findByIdUserAndIdSerie (idUser, imdb));

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

    private ViewingSerieCreateDto mapToDtoCreate(ViewingSerie viewingSerie) {
        ViewingSerieCreateDto viewingSerieCreateDto = new ViewingSerieCreateDto ();
        viewingSerieCreateDto.setStatus (viewingSerie.getStatus ());
        viewingSerieCreateDto.setImdb (viewingSerie.getSerie ().getImdbId ());
        viewingSerieCreateDto.setEmail (viewingSerie.getAppUser ().getEmail ());
        viewingSerieCreateDto.setCurrentSeason (viewingSerie.getCurrentSeason ());
        viewingSerieCreateDto.setCurrentEpisode (viewingSerie.getCurrentEpisode ());
        return viewingSerieCreateDto;
    }

    private ViewingSerie mapToDomainCreate(ViewingSerieCreateDto viewingSerieCreateDto) {
        ViewingSerie viewingSerie = new ViewingSerie ();
        viewingSerie.setStatus (viewingSerieCreateDto.getStatus ());
        viewingSerie.setSerie (serieRepository.findById (viewingSerieCreateDto.getImdb ()));
        viewingSerie.setCurrentSeason (viewingSerieCreateDto.getCurrentSeason ());
        viewingSerie.setCurrentEpisode (viewingSerieCreateDto.getCurrentEpisode ());
        viewingSerie.setAppUser (appUserRepository.findbyEmail (viewingSerieCreateDto.getEmail ()));
        return viewingSerie;
    }

    private ViewingSerieRestitDto mapToDtoRestit(ViewingSerie viewingSerie) {
        ViewingSerieRestitDto viewingSerieRestitDto = new ViewingSerieRestitDto ();
        viewingSerieRestitDto.setCurrentEpisode (viewingSerie.getCurrentEpisode ());
        viewingSerieRestitDto.setCurrentSeason (viewingSerie.getCurrentSeason ());
        viewingSerieRestitDto.setEmail (viewingSerie.getAppUser ().getEmail ());
        viewingSerieRestitDto.setSerieDto (mapperSerieDto.mapDomainToDto (viewingSerie.getSerie ()));
        viewingSerieRestitDto.setStatus (viewingSerie.getStatus ());
        return viewingSerieRestitDto;
    }

//    private ViewingSerie mapToDomainRestit(ViewingSerieRestitDto viewingSerieDto) {
//        ViewingSerie viewingSerie = new ViewingSerie ();
//
//        return viewingSerie;
//    }

}