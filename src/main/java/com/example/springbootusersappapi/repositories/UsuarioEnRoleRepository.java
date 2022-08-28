package com.example.springbootusersappapi.repositories;

import com.example.springbootusersappapi.models.UsuarioEnRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioEnRoleRepository extends JpaRepository<UsuarioEnRole, Long> {
    // Roles by user id


    @Query("SELECT r.nombre FROM UsuarioEnRole u JOIN u.role r WHERE u.user.id = :id")
    List<String> rolesByUsuarioId(@Param(value = "id") Long id);

}
