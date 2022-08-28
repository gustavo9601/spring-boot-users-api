package com.example.springbootusersappapi.controllers;

import com.example.springbootusersappapi.models.Role;
import com.example.springbootusersappapi.services.RoleService;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.Cacheable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
// @Secured({ "ROLE_admins" }) // Protegiendo todos los metodos del endpoint con el role admins
public class RoleController {

    @Autowired
    private RoleService roleRepository;

    // logger
    private Logger logger = LoggerFactory.getLogger(RoleController.class);

    @GetMapping
    @Secured({ "ROLE_admins" }) // protegiendo el endpoint con el role admins
    @Timed(value = "get-all-roles-timer") // nombre de la metrica
    public ResponseEntity<List<Role>> getRoles() {

        // Accediendo a la informacion del usuario que se autentico
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Usuario autenticado: " + authentication.getName());
        logger.info("Roles del usuario: " + authentication.getAuthorities());
        logger.info("Principal: " + authentication.getPrincipal());



        return new ResponseEntity<List<Role>>(this.roleRepository.getRoles(), HttpStatus.OK);
    }

    @GetMapping("/paginados")
    /*
    * PreAuthorize // Accede al metodo
    * PostAuthorize // Permite acceder al retorno de la funcion
    * */
    @PreAuthorize("hasRole('ROLE_admins') or hasRole('ROLE_supervisors')") // protegiendo el endpoint con el role admins o supervisors
    @PostAuthorize("hasRole('ROLE_admins')") // Solo los admins, recibirab una respuesta
    public ResponseEntity<Page<Role>> getRoles(@RequestParam(defaultValue = "0", value = "page") int page,
                                               @RequestParam(defaultValue = "10", value = "size") int size,
                                               @RequestParam(defaultValue = "", value = "observation") @Min(1) @Max(10) String nombre) {
        return new ResponseEntity<Page<Role>>(this.roleRepository.rolesPaginados(page, size), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        return new ResponseEntity<Role>(this.roleRepository.createRole(role), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@RequestBody Role role, @PathVariable Long id) {
        return new ResponseEntity<Role>(this.roleRepository.updateRole(role, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "roles", allEntries = true) // Borrara el cache cuando se ejecute
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        this.roleRepository.deleteRole(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<List<Role>> roleByNombre(@PathVariable String nombre) {
        return new ResponseEntity<List<Role>>(this.roleRepository.findByNombre(nombre), HttpStatus.OK);
    }

}
