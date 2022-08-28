package com.example.springbootusersappapi.services;

import com.example.springbootusersappapi.models.Role;
import com.example.springbootusersappapi.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import springfox.documentation.annotations.Cacheable;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Cacheable(value = "roles") // nombre del cache
    public List<Role> getRoles() {
        return this.roleRepository.findAll();
    }

    public Role createRole(Role role) {
        return this.roleRepository.save(role);
    }

    // updateRole
    public Role updateRole(Role role, Long id) {
        Role roleUpdate = this.findOrFail(id);
        roleUpdate.setNombre(role.getNombre());
        return this.roleRepository.save(roleUpdate);
    }

    // deleteRole
    public void deleteRole(Long id) {
        Role role = this.findOrFail(id);
        this.roleRepository.delete(role);
    }

    public Optional<Role> getRole(Long id) {
        return this.roleRepository.findById(id);
    }

    // findOrFail
    public Role findOrFail(Long id) {
        return this.roleRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role NO existe"));
    }

    public List<Role> findByNombre(String nombre) {
        return this.roleRepository.findByNombre(nombre);
    }

    public Page<Role> rolesPaginados(int page, int size){
        return this.roleRepository.findAll(PageRequest.of(page, size));
    }
}
