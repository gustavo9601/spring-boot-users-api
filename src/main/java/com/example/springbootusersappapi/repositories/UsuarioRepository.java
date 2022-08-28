package com.example.springbootusersappapi.repositories;

import com.example.springbootusersappapi.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT u FROM Usuario u WHERE u.id = :id and u.perfil.id = :perfilId")
    Optional<Usuario> findByIdAndPerfilId(@Param(value = "id") Long id,
                                          @Param(value = "perfilId") Long perfilId);
}
