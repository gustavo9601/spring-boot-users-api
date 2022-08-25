package com.example.springbootusersappapi.controllers;

import com.example.springbootusersappapi.models.Role;
import com.example.springbootusersappapi.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleRepository;

    @GetMapping
    public ResponseEntity<List<Role>> getRoles() {
        return new ResponseEntity<List<Role>>(this.roleRepository.getRoles(), HttpStatus.OK);
    }

    @GetMapping("/paginados")
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
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        this.roleRepository.deleteRole(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<List<Role>> roleByNombre(@PathVariable String nombre) {
        return new ResponseEntity<List<Role>>(this.roleRepository.findByNombre(nombre), HttpStatus.OK);
    }

}
