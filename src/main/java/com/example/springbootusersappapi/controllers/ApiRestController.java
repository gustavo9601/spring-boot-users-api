package com.example.springbootusersappapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/")
public class ApiRestController {

    @GetMapping(value = "")
    public String getHello() {
        return "Hello";
    }

}
