package com.example.springbootusersappapi.services;

import com.example.springbootusersappapi.models.User;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private Faker faker;

    private List<User> users = new ArrayList<>();

    // Se ejecutara despues de inyectar las dependencias
    @PostConstruct
    public void init() {
        for (int i = 0; i < 100; i++) {
            this.users.add(new User(
                    faker.name().firstName(),
                    faker.name().username(),
                    faker.internet().password()));
        }
        System.out.println("users = " + this.users);
    }


    public List<User> getUsers() {
        return users;
    }

    public User getUserByUsername(String username) {
        System.out.println("username = " + username);
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado")
                );
    }

    public Optional<User> existeUsuario(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    public User createUser(User user) {
        if (this.existeUsuario(user.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuario ya existe");
        }
        users.add(user);
        return user;
    }

    public User updateUser(User user, String username) {
        Optional<User> userToUpdate = this.existeUsuario(username);
        if (userToUpdate.isPresent()) {
            userToUpdate.get().setNickName(user.getNickName());
            userToUpdate.get().setPassword(user.getPassword());
            return userToUpdate.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario NO existe");
    }

    public Boolean deleteUser(String username) {
        Optional<User> userToRemove = this.existeUsuario(username);
        if (userToRemove.isPresent()) {
            return users.remove(userToRemove.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario NO existe");
    }

    public List<User> getUserStartWithNickName(String startWith) {

        if (startWith == null || startWith.isEmpty() || startWith.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El parametro startWith no puede estar vacio");
        }

        return this.users.stream()
                .filter(user -> user.getNickName().startsWith(startWith)) // Filtramos lo que empiece por el parametro
                .collect(Collectors.toList()); // Transformamos de stream a list
    }
}
