package org.example.domain.util;


import org.example.domain.entity.City;
import org.example.domain.entity.Country;
import org.example.domain.entity.CountryLanguage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class ConnectionFactory implements AutoCloseable{
    private final SessionFactory sessionFactory;

    public ConnectionFactory(){
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(City.class);
        configuration.addAnnotatedClass(Country.class);
        configuration.addAnnotatedClass(CountryLanguage.class);
        Properties properties = new Properties();
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        configuration.setProperties(properties);
        sessionFactory = configuration.buildSessionFactory();
    }



    public SessionFactory getSessionFactory()  {
        Session session = sessionFactory.openSession();
        return sessionFactory;
    }

    @Override
    public void close() {
        sessionFactory.close();
    }
}
