package com.example.springbootusersappapi.repositories;

import com.example.springbootusersappapi.models.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}
