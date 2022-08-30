package com.example.springbootusersappapi.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {

    // Consumer
    private Map<String, Object> consumerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9098"); // cluster de kafka
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "gus-group"); // identificador como consumidor
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true); // cuando reciba los mensajes los comiteara
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100"); // el autocomit se realizara cada 100 ms
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

    // Producer
    private Map<String, Object> producerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9098"); // cluster de kafka
        props.put(ProducerConfig.RETRIES_CONFIG, 0); // Numero de reintentos
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384); // tamaño del batch, grupo de mensaje
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1); // tiempo de espera para enviar los mensajes
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432); // memoria de buffer, limitara el tamaño del grupo del batch
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class); // llave de tipo entero
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class); // valor de tipo string


        return props;
    }

   // Configuracion para enviar mensajes a la cola
    @Bean
    public KafkaTemplate<Integer, String> createTempplate(){
        Map<String, Object> senderPropos = this.producerProps();
        ProducerFactory<Integer, String> producerFactory = new DefaultKafkaProducerFactory<>(senderPropos);
        KafkaTemplate<Integer, String> template = new KafkaTemplate<>(producerFactory);
        return template;
    }

}
