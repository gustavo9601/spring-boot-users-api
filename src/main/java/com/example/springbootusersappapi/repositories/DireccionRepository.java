package com.example.springbootusersappapi.repositories;

import com.example.springbootusersappapi.models.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DireccionRepository extends JpaRepository<Direccion, Long> {

    @Query("SELECT d, p FROM Direccion d  join d.perfil p where  d.perfil.id = :perfilId")
    public List<Direccion> findByPerfilId(@Param(value = "perfilId") Long id);

}
