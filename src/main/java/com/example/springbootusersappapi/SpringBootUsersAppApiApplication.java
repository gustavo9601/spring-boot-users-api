package com.example.springbootusersappapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;



@SpringBootApplication
public class SpringBootUsersAppApiApplication implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;
    private Logger logger = LoggerFactory.getLogger(SpringBootUsersAppApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootUsersAppApiApplication.class, args);
    }

    @Override
    public void run(String ...args) throws Exception{
        logger.info("Contraseña encode");
        logger.info("admin:\t" + this.passwordEncoder.encode("admin"));
        logger.info("user:\t" + this.passwordEncoder.encode("user"));
    }
}
