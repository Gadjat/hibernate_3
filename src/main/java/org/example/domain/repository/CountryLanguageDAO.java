package org.example.domain.repository;

import org.example.domain.entity.CountryLanguage;
import org.example.domain.util.ConnectionFactory;

public class CountryLanguageDAO extends AbstractDAO<CountryLanguage>{

    public CountryLanguageDAO(Class<CountryLanguage> clazzToSet, ConnectionFactory connectionFactory) {
        super(clazzToSet, connectionFactory);
    }
}
