package com.example.springbootusersappapi.models;

import javax.persistence.*;

@Entity
@Table(name = "usuarios_en_role")
public class UsuarioEnRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id") // campo en la tabla usuarios_en_role
    private Usuario user;

    @ManyToOne
    @JoinColumn(name = "role_id") // campo en la tabla usuarios_en_role
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
