package org.example.domain.repository;

import org.example.domain.entity.City;
import org.example.domain.util.ConnectionFactory;

public class CityDAO extends AbstractDAO<City>{


    public CityDAO(Class<City> clazzToSet, ConnectionFactory connectionFactory) {
        super(clazzToSet, connectionFactory);
    }
}
