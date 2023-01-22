package org.example.domain.services;

import io.lettuce.core.RedisClient;
import org.example.domain.entity.City;
import org.example.domain.entity.Country;
import org.example.domain.entity.CountryLanguage;
import org.example.domain.redis.CityCountry;
import org.example.domain.redis.Language;
import org.example.domain.repository.CityDAO;
import org.example.domain.repository.CountryDAO;
import org.example.domain.repository.CountryLanguageDAO;
import org.example.domain.util.ConnectionFactory;
import org.example.domain.util.RedisConnectionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class RedisVsMySqlService {
    private final SessionFactory sessionFactory;

    private final RedisClient redisClient;
    private final RedisConnectionFactory redisConnectionFactory = new RedisConnectionFactory();

    private final CityDAO cityDAO;
    private final CountryDAO countryDAO;

    private final CountryLanguageDAO countryLanguageDAO;


    public RedisVsMySqlService(){
        sessionFactory = new ConnectionFactory().getSessionFactory();
        redisClient = redisConnectionFactory.getRedisClient();
        cityDAO = new CityDAO(City.class, sessionFactory);
        countryDAO = new CountryDAO(Country.class, sessionFactory);
        countryLanguageDAO = new CountryLanguageDAO(CountryLanguage.class, sessionFactory);
    }


    public void shutdown() throws Exception {
        if (nonNull(sessionFactory)) {
            sessionFactory.close();
        }
        if (nonNull(redisClient)) {
            redisClient.shutdown();
        }
    }


    public List<City> fetchData(RedisVsMySqlService redisVsMySqlService) {
        try (Session session = redisVsMySqlService.sessionFactory.openSession()) {
            List<City> allCities = new ArrayList<>();
            session.beginTransaction();

            List<Country> countries = redisVsMySqlService.countryDAO.getAll();

            int totalCount = redisVsMySqlService.cityDAO.getTotalCount();
            int step = 500;
            for (int i = 0; i < totalCount; i += step) {
                allCities.addAll(redisVsMySqlService.cityDAO.getItems(i, step));
            }
            session.getTransaction().commit();
            return allCities;
        }
    }

    public List<CityCountry> transformData(List<City> cities){
        return cities.stream().map(city ->{
            CityCountry res = new CityCountry();
            res.setId(city.getId());
            res.setName(city.getName());
            res.setPopulation(city.getPopulation());
            res.setDistrict(city.getDistrict());

            Country country = city.getCountry();
            res.setAlternativeCountryCode(country.getAlternativeCode());
            res.setContinent(country.getContinent());
            res.setCountryCode(country.getCode());
            res.setCountryName(country.getName());
            res.setCountryPopulation(country.getPopulation());
            res.setCountryRegion(country.getRegion());
            res.setCountrySurfaceArea(country.getSurfaceArea());

            Set<CountryLanguage> countryLanguageSet = country.getLanguages();
            Set<Language> languages= countryLanguageSet.stream().map(lang -> {
                Language language = new Language();
                language.setLanguage(lang.getLanguage());
                language.setOfficial(lang.getOfficial());
                language.setPercentage(lang.getPercentage());
                return language;
            }).collect(Collectors.toSet());
            res.setLanguages(languages);
            return res;
        }).collect(Collectors.toList());
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public RedisClient getRedisClient() {
        return redisClient;
    }

    public RedisConnectionFactory getRedisConnectionFactory() {
        return redisConnectionFactory;
    }

    public CityDAO getCityDAO() {
        return cityDAO;
    }

    public CountryDAO getCountryDAO() {
        return countryDAO;
    }

    public CountryLanguageDAO getCountryLanguageDAO() {
        return countryLanguageDAO;
    }
}
