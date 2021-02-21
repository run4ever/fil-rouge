package fr.epita.filrouge.application.services;

import fr.epita.filrouge.application.dto.serie.SerieDTO;

/**
 * Liste des services pour l'objet Serie
 * @author : Yoss
 */
public interface ISerieManagement {


    SerieDTO getSerieById(String id);


    SerieDTO createSerie(SerieDTO serieDTO);
}
