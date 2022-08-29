package com.example.springbootusersappapi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerEjemplo {

    // logger
    private static final Logger logger = LoggerFactory.getLogger(KafkaListenerEjemplo.class);

    // topics = // se le puede escepficiar un arreglo de varios topics a escuchar
    @KafkaListener(topics = "gus-topic", groupId = "gus-group")
    public void listen(String message) {
        logger.info("Mensaje recibido: " + message);
    }
}
