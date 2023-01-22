package org.example.domain.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;
import org.example.domain.redis.CityCountry;

import java.util.List;

public class RedisConnectionFactory {

    private final RedisClient redisClient;

    private final ObjectMapper mapper;

    public RedisConnectionFactory(){
        this.mapper = new ObjectMapper();
        redisClient = prepareRedisClient();
    }

    private RedisClient prepareRedisClient(){
        RedisClient redisClient = RedisClient.create(RedisURI.create("localhost",6379));
        try(StatefulRedisConnection<String, String> connection = redisClient.connect()){
            System.out.println("\nConnected to Redis\n");
        }
        return redisClient;
    }

    public RedisClient getRedisClient(){
        return redisClient;
    }

    public void pushToRedis(List<CityCountry> data) {
        try (StatefulRedisConnection<String, String> connection = redisClient.connect()) {
            RedisStringCommands<String, String> sync = connection.sync();
            for (CityCountry cityCountry : data) {
                try {
                    sync.set(String.valueOf(cityCountry.getId()), mapper.writeValueAsString(cityCountry));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void testRedisData(List<Integer> ids) {
        try (StatefulRedisConnection<String, String> connection = redisClient.connect()) {
            RedisStringCommands<String, String> sync = connection.sync();
            for (Integer id : ids) {
                String value = sync.get(String.valueOf(id));
                try {
                    mapper.readValue(value, CityCountry.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
