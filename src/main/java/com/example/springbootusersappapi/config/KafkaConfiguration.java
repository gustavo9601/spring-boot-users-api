package com.example.springbootusersappapi.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {

    // Consumer
    @Bean
    public Map<String, Object> consumerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092"); // cluster de kafka
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "gus-group"); // identificador como consumidor
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true); // cuando reciba los mensajes los comiteara
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100"); // el automocit se realizara cada 100 ms
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class); // llave de tipo entero
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class); // valor de tipo string
        return props;
    }

    // Factoria del consumidor
    @Bean
    public ConsumerFactory<Integer, String> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(this.consumerProps());
    }

    // Configuracion para escuchar la cola
    @Bean
    public ConcurrentKafkaListenerContainerFactory<Integer, String> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(this.consumerFactory());
        return factory;
    }

}
