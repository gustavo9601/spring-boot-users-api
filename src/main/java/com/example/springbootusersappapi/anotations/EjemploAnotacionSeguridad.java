package com.example.springbootusersappapi.anotations;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Sirve para agrupar varias anotaciones en una sola.
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole('ROLE_admins') or hasRole('ROLE_supervisors')") // protegiendo el endpoint con el role admins o supervisors
@PostAuthorize("hasRole('ROLE_admins')") // Solo los admins, recibirab una respuesta
public @interface EjemploAnotacionSeguridad {
}
