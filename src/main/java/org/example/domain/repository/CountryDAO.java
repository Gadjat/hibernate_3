package org.example.domain.repository;

import org.example.domain.util.ConnectionFactory;

public class CountryDAO extends AbstractDAO{
    public CountryDAO(Class clazzToSet, ConnectionFactory connectionFactory) {
        super(clazzToSet, connectionFactory);
    }
}