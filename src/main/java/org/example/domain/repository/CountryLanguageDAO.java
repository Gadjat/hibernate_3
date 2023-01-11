package org.example.domain.repository;

import org.example.domain.util.ConnectionFactory;

public class CountryLanguageDAO extends AbstractDAO{
    public CountryLanguageDAO(Class clazzToSet, ConnectionFactory connectionFactory) {
        super(clazzToSet, connectionFactory);
    }
}
