package org.example.domain.repository;

import org.example.domain.entity.Country;
import org.example.domain.util.ConnectionFactory;

public class CountryDAO extends AbstractDAO<Country>{

    public CountryDAO(Class<Country> clazzToSet, ConnectionFactory connectionFactory) {
        super(clazzToSet, connectionFactory);
    }
}
