package org.example.domain.repository;

import org.example.domain.entity.Country;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class CountryDAO extends AbstractDAO<Country>{

    public CountryDAO(Class<Country> clazzToSet, SessionFactory sessionFactory) {
        super(clazzToSet, sessionFactory);
    }
    @Override
    public List<Country> getAll() {
        getSessionFactory().getCurrentSession().beginTransaction();
        Query<Country> query = getSessionFactory().getCurrentSession().createQuery("select c from Country c join fetch c.languages" , Country.class);

        return query.list();
    }
}
