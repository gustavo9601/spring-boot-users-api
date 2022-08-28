package com.example.springbootusersappapi.config;

import com.example.springbootusersappapi.models.Usuario;
import com.example.springbootusersappapi.repositories.UsuarioEnRoleRepository;
import com.example.springbootusersappapi.repositories.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioDetailService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Autowired
    private UsuarioEnRoleRepository usuarioEnRoleRepository;

    // logger
    private Logger logger = LoggerFactory.getLogger(UsuarioDetailService.class);


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Obtenemos que exista el usuario
        Optional<Usuario> usuario = this.usuarioRepository.findByUsername(username);
        logger.info("Usuario: " + usuario);
        if (usuario.isPresent()) {
            // Si existe traemos sus roles
            List<String> roles = this.usuarioEnRoleRepository.rolesByUsuarioId(usuario.get().getId());

            logger.info("Roles: " + roles);

            return User.withUsername(usuario.get().getUsername()) // Seteamos el usuername de la sesion para el usuario
                    .password(usuario.get().getPassword()) // seteamos password
                    .roles(roles.toArray(new String[roles.size()])) // steamos como array sus roles
                    .build();
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }
}
