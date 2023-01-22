package org.example.domain.repository;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import java.util.List;

public abstract class AbstractDAO<T> {
    private final Class<T> clazz;
    private final SessionFactory sessionFactory;


    public AbstractDAO(final Class<T> clazzToSet, SessionFactory sessionFactory)   {
        this.clazz = clazzToSet;
        this.sessionFactory = sessionFactory;
    }

    public T getById(final long id) {
        return (T) sessionFactory.getCurrentSession().get(clazz, id);
    }

    public List<T> getItems(int from, int count) {
        Query<T> query = sessionFactory.getCurrentSession().createQuery("from " + clazz.getName(), clazz);
        query.setFirstResult(from);
        query.setMaxResults(count);
        return query.getResultList();
    }

    public List<T> getAll() {
        Query<T> query = sessionFactory.getCurrentSession().createQuery("select c from " + clazz.getName()+ " c ", clazz);
        return query.list();
    }

    public List<T> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from " + clazz.getName(), clazz).list();
    }
    public int getTotalCount() {
        Query<Long> query = sessionFactory.getCurrentSession().createQuery("select count(c) from " + clazz.getName() + " c ", Long.class);
        return Math.toIntExact(query.uniqueResult());
    }

    public T create(final T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    public T update(final T entity) {
        return (T) sessionFactory.getCurrentSession().merge(entity);
    }

    public void delete(final T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    public void deleteById(final long entityId) {
        final T entity = getById(entityId);
        delete(entity);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
