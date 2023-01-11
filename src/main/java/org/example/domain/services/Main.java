package org.example.domain.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.RedisClient;
import org.example.domain.entity.City;
import org.example.domain.entity.Country;
import org.example.domain.repository.CityDAO;
import org.example.domain.repository.CountryDAO;
import org.example.domain.repository.CountryLanguageDAO;
import org.example.domain.util.ConnectionFactory;

import static java.util.Objects.nonNull;

public class Main {

    private final ConnectionFactory connectionFactory;
    private final RedisClient redisClient;

    private final ObjectMapper mapper;

    private final CityDAO cityDAO;
    private final CountryDAO countryDAO;

    private final CountryLanguageDAO countryLanguageDAO;


    public Main(){
        connectionFactory = new ConnectionFactory();
        redisClient = prepareRedisClient();
        cityDAO = new CityDAO(City.class, connectionFactory);
        countryDAO = new CountryDAO(Country.class, connectionFactory);
        countryLanguageDAO = new CountryLanguageDAO(CountryLanguageDAO.class, connectionFactory);
        mapper = new ObjectMapper();
    }

    private void shutdown() throws Exception {
        if (nonNull(connectionFactory)) {
            connectionFactory.close();
        }
        if (nonNull(redisClient)) {
            redisClient.shutdown();
        }
    }



}
