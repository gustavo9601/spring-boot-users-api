package com.example.springbootusersappapi.config;

import com.example.springbootusersappapi.repositories.UsuarioEnRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,
prePostEnabled = true)
/*
* securedEnabled = true // habilita la anotacion @Secured
* prePostEnabled = true // habilita la anotacion @PreAuthorize y @PostAuthorize
* */
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

       /*
        * Generando en memoria el usuario para dev
        *
        *
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(this.passwordEncoder().encode("admin"))
                .roles("ADMIN");


    }
     * */


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // desactivamos el CSRF
                .authorizeRequests() // Restringe el acceso a recursos
                .antMatchers("/api/roles/**") // recursos a proteger
                .hasRole("admins") // define que roles pueden acceder a los recursos
                .antMatchers("/api/users/**").permitAll().anyRequest().authenticated() // Solo necesita que se autentique para acceder a /api/users
                .and()
                .httpBasic(); // Define el tipo de autenticacion que seria basico
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
