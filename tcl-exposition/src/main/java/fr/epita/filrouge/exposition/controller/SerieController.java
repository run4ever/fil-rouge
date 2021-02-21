package fr.epita.filrouge.exposition.controller;

import fr.epita.filrouge.application.dto.serie.SerieDTO;
import fr.epita.filrouge.application.services.ISerieManagement;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/serie")
@Api(value = "Controller REST pour les series")
public class SerieController {

    private static Logger logger = LoggerFactory.getLogger(SerieController.class);

    @Autowired
    private ISerieManagement iSerieManagement;

    @GetMapping("/{id}")
    public ResponseEntity<SerieDTO> getSerie(@PathVariable("id") String imdbId) {

        logger.info("imdb Id en entrée : " + imdbId);
        return new ResponseEntity<SerieDTO>(iSerieManagement.getSerieById(imdbId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SerieDTO> createSerie(@RequestBody SerieDTO serieDTO) {

        return new ResponseEntity<SerieDTO>(iSerieManagement.createSerie (serieDTO), HttpStatus.OK);
    }


}
