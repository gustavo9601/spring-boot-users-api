package com.example.springbootusersappapi.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
@ComponentScan
public class CacheConfiguration {

    @Bean
    public CacheManager cacheManager(RedissonClient redissonClient) throws IOException {
        /*
        * Configuracion cache sin redis
        *
        *
        // Se crea un cache en memoria
        // return new ConcurrentMapCacheManager("users", "roles", "address"); // se especifican las llaves del cache
        return new ConcurrentMapCacheManager("users", "roles")
        * */

        Map<String, CacheConfig> cacheConfig = new HashMap<>();
        // Indices de cache
        cacheConfig.put("users", new CacheConfig(24*60*1000, 12*60*1000));
        cacheConfig.put("roles", new CacheConfig(24*60*1000, 12*60*1000));
        return new RedissonSpringCacheManager(redissonClient, cacheConfig);

    }


    // Regsitrando el cliente de redisson
    @Bean(destroyMethod = "shutdown") // Se destruira si se detiene la aplicacion
    public RedissonClient redissonClient() {
        Config config = new Config();
        // Seteamos la configuracion
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        return Redisson.create(config);
    }
}
