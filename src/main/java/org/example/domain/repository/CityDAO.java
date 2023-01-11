package org.example.domain.repository;

import org.example.domain.util.ConnectionFactory;

public class CityDAO extends AbstractDAO{

    public CityDAO(Class clazzToSet, ConnectionFactory connectionFactory) {
        super(clazzToSet, connectionFactory);
    }
}
