package org.example.domain.repository;

import org.example.domain.entity.City;
import org.example.domain.entity.CountryLanguage;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Set;

public class CityDAO extends AbstractDAO<City>{


    public CityDAO(Class<City> clazzToSet, SessionFactory sessionFactory) {
        super(clazzToSet, sessionFactory);
    }
    public City getById(Integer id) {
        Query<City> query = getSessionFactory().getCurrentSession().createQuery("select c from City c join fetch c.country where c.id = :ID", City.class);
        query.setParameter("ID", id);
        return query.getSingleResult();
    }

    public void testMysqlData(List<Integer> ids) {
        try (Session session = getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            for (Integer id : ids) {
                City city = getById(id);
                Set<CountryLanguage> languages = city.getCountry().getLanguages();
            }
            session.getTransaction().commit();
        }
    }
}
