package org.example.domain.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.RedisClient;
import org.example.domain.entity.City;
import org.example.domain.entity.Country;
import org.example.domain.entity.CountryLanguage;
import org.example.domain.repository.CityDAO;
import org.example.domain.repository.CountryDAO;
import org.example.domain.repository.CountryLanguageDAO;
import org.example.domain.util.ConnectionFactory;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class Main {

    private final ConnectionFactory connectionFactory;
    //private final RedisClient redisClient;

    private final ObjectMapper mapper;

    private final CityDAO cityDAO;
    private final CountryDAO countryDAO;

    private final CountryLanguageDAO countryLanguageDAO;


    public Main(){
        connectionFactory = new ConnectionFactory();
        //redisClient = prepareRedisClient();
        cityDAO = new CityDAO(City.class, connectionFactory);
        countryDAO = new CountryDAO(Country.class, connectionFactory);
        countryLanguageDAO = new CountryLanguageDAO(CountryLanguage.class, connectionFactory);
        mapper = new ObjectMapper();
    }

    private void shutdown() throws Exception {
        if (nonNull(connectionFactory)) {
            connectionFactory.close();
        }
//        if (nonNull(redisClient)) {
//            redisClient.shutdown();
//        }
    }

    private List<City> fetchData(Main main) {
        try (Session session = main.connectionFactory.open()) {
            List<City> allCities = new ArrayList<>();
            session.beginTransaction();

            List<Country> countries = main.countryDAO.getAll();

            int totalCount = main.cityDAO.getTotalCount();
            int step = 500;
            for (int i = 0; i < totalCount; i += step) {
                allCities.addAll(main.cityDAO.getItems(i, step));
            }
            session.getTransaction().commit();
            return allCities;
        }
    }
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        List<City> allCities = main.fetchData(main);
        main.shutdown();
    }


}
