package fr.epita.filrouge.application.viewingserie;

import fr.epita.filrouge.application.common.PageDTO;
import fr.epita.filrouge.application.mapper.ViewingSerieDtoMapper;
import fr.epita.filrouge.domain.entity.person.AppUserRepository;
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
    private ViewingSerieRepository viewingSerieRepository;


    @Autowired
    private ViewingSerieDtoMapper viewingSerieDtoMapper;



    /**
     * Création d'un nouveau visionnage
     *
     * @param serieViewDto
     */
    @Override
    public ViewingSerieCreateDto create(ViewingSerieCreateDto serieViewDto) {

        if (viewingSerieRepository.findByIdUserAndIdSerie (serieViewDto.getEmail (), serieViewDto.getImdbId()) != null) {
            throw new AlreadyExistingException ("ViewingSerie existing : Id imdb : " + serieViewDto.getImdbId()
                    + " user : " + serieViewDto.getEmail (), ErrorCodes.VIEWVING_SERIE_ALREADY_EXISTING);
        }

        return viewingSerieDtoMapper.mapToDtoCreate (viewingSerieRepository.create (viewingSerieDtoMapper.mapToDomainCreate (serieViewDto)));

    }

    /**
     * Récupération de la liste des visionnages pour un user donné.
     * Méthode sans pagination.
     *
     * @param email
     * @return liste de visionnage
     */
    @Override
    public List<ViewingSerieRestitDto> findByUserAllVievingSerieDto(String email) {

        if (appUserRepository.findbyEmail (email) == null) {
            throw new NotFoundException ("User inconnu : " + email, ErrorCodes.USER_NOT_FOUND);
        }
        List<ViewingSerieRestitDto> viewingSerieRestitDtos = new ArrayList<ViewingSerieRestitDto> ();
        for (ViewingSerie viewingSerie : viewingSerieRepository.findallViewingSerieByUser (email)) {
            viewingSerieRestitDtos.add (viewingSerieDtoMapper.mapToDtoRestit (viewingSerie));
        }
        return viewingSerieRestitDtos;
    }

    /**
     * Récupération de la liste des visionnages pour un user donné.
     * Méthode avec pagination.
     *
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
            attributeVerify = "".concat (sortAttribute);
        } else {
            attributeVerify = "".concat ("DateLastAction");
        }
        for (ViewingSerie viewingSerie : viewingSerieRepository.findallViewingSerieByUserByPage (email, offset, limit, attributeVerify, sortAsc)) {
            viewingSerieRestitDtos.add (viewingSerieDtoMapper.mapToDtoRestit (viewingSerie));
        }
        pageDTO.setLimit (limit);
        pageDTO.setOffset (offset);
        pageDTO.setSortAttribute (sortAttribute);
        pageDTO.setTotal (viewingSerieRepository.countTotalViewingSerieByUser (email));
        pageDTO.setListViewingOrSerieOrVideo (viewingSerieRestitDtos);
        return pageDTO;
    }

    /**
     * Affichage d'un visionnage donné
     *
     * @param idUser
     * @param imdb
     * @return
     */
    @Override
    public ViewingSerieRestitDto findViewing(String idUser, String imdb) {

        return viewingSerieDtoMapper.mapToDtoRestit (viewingSerieRepository.findByIdUserAndIdSerie (idUser, imdb));

    }

    @Override
    public ViewingSerieCreateDto updateViewingSerieStatus(ViewingSerieCreateDto viewingSerieCreateDto) {

        final ViewingSerie vs = viewingSerieRepository.findByIdUserAndIdSerie (viewingSerieCreateDto.getEmail (),
                viewingSerieCreateDto.getImdbId());
        vs.setStatus(viewingSerieCreateDto.getStatus());
        //ACH : set saison et episode si les données ne sont pas null
        if(viewingSerieCreateDto.getCurrentSeason() != null) {
            vs.setCurrentSeason(viewingSerieCreateDto.getCurrentSeason());
        }
        if(viewingSerieCreateDto.getCurrentEpisode()!= null) {
            vs.setCurrentEpisode(viewingSerieCreateDto.getCurrentEpisode());
        }
       return viewingSerieDtoMapper.mapToDtoCreate(viewingSerieRepository.update(vs));
    }

    @Override
    public void deleteViewingSerie(ViewingSerieCreateDto ViewingSerieCreateDto) {

        final ViewingSerie vs = viewingSerieRepository.findByIdUserAndIdSerie (ViewingSerieCreateDto.getEmail ()
        ,ViewingSerieCreateDto.getImdbId());

        viewingSerieRepository.delete(vs);
    }




    /**
     * Controle par introspection d'un critère de tri.
     * La méthode vérifie la présence de l'attribut
     *
     * @param attribute
     * @return
     */
    private boolean controlSortAttribute(String attribute) {

        for (Field field : ViewingSerie.class.getFields ()) {
            if (field.getName ().equalsIgnoreCase (attribute)) {
                return true;
            }
        }
        return false;
    }

}
