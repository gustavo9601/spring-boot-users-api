package com.example.springbootusersappapi.controllers;

import com.example.springbootusersappapi.models.Perfil;
import com.example.springbootusersappapi.models.Usuario;
import com.example.springbootusersappapi.repositories.UsuarioRepository;
import com.example.springbootusersappapi.services.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/users/{userId}/perfiles") // Recibira el id del usuario
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity<Perfil> create(@PathVariable Long userId,
                                         @RequestBody Perfil perfil) {
        Optional<Usuario> usuario = this.usuarioRepository.findById(userId);
        if (usuario.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no existe");
        }
        return new ResponseEntity<Perfil>(this.perfilService.create(userId, perfil), HttpStatus.CREATED);
    }

    public ResponseEntity<Perfil> getPerfil(@PathVariable Long userId,
                                            @PathVariable Long perfilId) {
        Optional<Usuario> usuario = this.usuarioRepository.findById(userId);
        if (usuario.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no existe");
        }
        return new ResponseEntity<Perfil>(this.perfilService.getPerfil(userId, perfilId), HttpStatus.OK);
    }

}
