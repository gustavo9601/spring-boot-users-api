package com.example.springbootusersappapi.repositories;

import com.example.springbootusersappapi.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "SELECT * FROM roles WHERE nombre like %:nombre% limit 5", nativeQuery = true)
    public List<Role> findByNombre(@Param(value = "nombre") String nombre);


}
