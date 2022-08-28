package com.example.springbootusersappapi.services;

import com.example.springbootusersappapi.models.Perfil;
import com.example.springbootusersappapi.repositories.PerfilRepository;
import org.springframework.stereotype.Service;

@Service
public class PerfilService {

    private PerfilRepository perfilRepository;

    public Perfil create(Long id, Perfil profile) {
        return this.perfilRepository.save(profile);
    }

    public Perfil getPerfil(Long perfilId, Long userId) {
        return this.perfilRepository.getById(perfilId);
    }
}
