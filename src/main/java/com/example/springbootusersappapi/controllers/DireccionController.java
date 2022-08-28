package com.example.springbootusersappapi.controllers;

import com.example.springbootusersappapi.models.Direccion;
import com.example.springbootusersappapi.models.Perfil;
import com.example.springbootusersappapi.repositories.DireccionRepository;
import com.example.springbootusersappapi.repositories.PerfilRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/perfiles/{perfilId}/direcciones") // Recibira el id del perfil
public class DireccionController {

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    private static final Logger logger = LoggerFactory.getLogger(DireccionController.class);


    @GetMapping
    public ResponseEntity<List<Direccion>> getDirecciones(@PathVariable Long perfilId) {
        return new ResponseEntity<List<Direccion>>(this.direccionRepository.findByPerfilId(perfilId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Direccion> create(@PathVariable Long perfilId,
                                            @RequestBody Direccion direccion) {

        Perfil perfil = this.perfilRepository.getById(perfilId);
        direccion.setPerfil(perfil);

        logger.info("Direccion a crear: " + direccion);
        return new ResponseEntity<Direccion>(this.direccionRepository.save(direccion), HttpStatus.CREATED);
    }

}
