package org.example.domain.util;

import lombok.SneakyThrows;
import org.example.domain.entity.City;
import org.example.domain.entity.Country;
import org.example.domain.entity.CountryLanguage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConnectionFactory implements AutoCloseable{
    private final SessionFactory sessionFactory;
    @SneakyThrows
    public ConnectionFactory(){
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(City.class);
        configuration.addAnnotatedClass(Country.class);
        configuration.addAnnotatedClass(CountryLanguage.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    @SneakyThrows
    public Session open()  {
        try(ConnectionFactory creator = new ConnectionFactory()){
            return creator.sessionFactory.openSession();
        }

    }

    @Override
    public void close() throws Exception {
        sessionFactory.close();
    }
}
