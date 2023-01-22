package org.example.domain.repository;

import org.example.domain.entity.CountryLanguage;
import org.hibernate.SessionFactory;

public class CountryLanguageDAO extends AbstractDAO<CountryLanguage>{

    public CountryLanguageDAO(Class<CountryLanguage> clazzToSet, SessionFactory sessionFactory) {
        super(clazzToSet, sessionFactory);
    }
}
