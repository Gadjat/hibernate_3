package org.example.domain.repository;

import org.example.domain.util.ConnectionFactory;
import org.hibernate.Session;

import org.hibernate.query.Query;
import java.util.List;

public abstract class AbstractDAO<T> {
    private final Class<T> clazz;
    private final ConnectionFactory connectionFactory;

    public AbstractDAO(final Class<T> clazzToSet, ConnectionFactory connectionFactory)   {
        this.clazz = clazzToSet;
        this.connectionFactory = connectionFactory;
    }

    public T getById(final long id) {
        return (T) getCurrentSession().get(clazz, id);
    }

    public List<T> getItems(int from, int count) {
        Query<T> query = getCurrentSession().createQuery("from " + clazz.getName(), clazz);
        query.setFirstResult(from);
        query.setMaxResults(count);
        return query.getResultList();
    }

    public List<T> getAll() {
        Query<T> query = getCurrentSession().createQuery("select c from " + clazz.getName()+ " c ", clazz);
        return query.list();
    }

    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getName(), clazz).list();
    }
    public int getTotalCount() {
        Query<Long> query = getCurrentSession().createQuery("select count(c) from " + clazz.getName() + " c ", Long.class);
        return Math.toIntExact(query.uniqueResult());
    }

    public T create(final T entity) {
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    public T update(final T entity) {
        return (T) getCurrentSession().merge(entity);
    }

    public void delete(final T entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteById(final long entityId) {
        final T entity = getById(entityId);
        delete(entity);
    }

    protected Session getCurrentSession() {
        return connectionFactory.open();
    }
}
