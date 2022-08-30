package com.example.springbootusersappapi.services;

import com.example.springbootusersappapi.models.AuditDetails;
import com.example.springbootusersappapi.models.Role;
import com.example.springbootusersappapi.repositories.RoleRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import springfox.documentation.annotations.Cacheable;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    // Inyectara el kafkaTemplate para enviar mensajes a kafka
    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;


    private Logger logger = LoggerFactory.getLogger(RoleService.class);

    // Permitira convertir de clase a json
    private ObjectMapper mapper = new ObjectMapper();

    @Cacheable(value = "roles") // nombre del cache
    public List<Role> getRoles() {
        return this.roleRepository.findAll();
    }

    public Role createRole(Role role) {

        Role roleCreated = this.roleRepository.save(role);

        String userNameAuthenticated = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info("Usuario autenticado: " + userNameAuthenticated);
        AuditDetails auditDetails = new AuditDetails(userNameAuthenticated, roleCreated.getNombre());
        logger.info("auditDetails: " + auditDetails);

        try {
            String auditDetailsJsonString = this.mapper.writeValueAsString(auditDetails);
            logger.info("auditDetailsJsonString: " + auditDetailsJsonString);
            // Enviara el mensaje a kafka
            this.kafkaTemplate.send("gus-topic", auditDetailsJsonString);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al convertir a json");
        }

        return roleCreated;
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
