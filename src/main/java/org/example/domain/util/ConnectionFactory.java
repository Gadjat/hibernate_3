package org.example.domain.util;


import org.example.domain.entity.City;
import org.example.domain.entity.Country;
import org.example.domain.entity.CountryLanguage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConnectionFactory implements AutoCloseable{
    private final SessionFactory sessionFactory;

    public ConnectionFactory(){
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(City.class);
        configuration.addAnnotatedClass(Country.class);
        configuration.addAnnotatedClass(CountryLanguage.class);
        sessionFactory = configuration.buildSessionFactory();
    }


    public Session open()  {
        return sessionFactory.openSession();
    }

    @Override
    public void close() {
        sessionFactory.close();
    }
}
