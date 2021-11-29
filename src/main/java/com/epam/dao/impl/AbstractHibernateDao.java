package com.epam.dao.impl;

import com.epam.dao.BaseDao;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class AbstractHibernateDao<T extends Serializable> implements BaseDao<T> {

  private Class<T> clazz;

  @Autowired
  protected SessionFactory sessionFactory;

  public AbstractHibernateDao(Class<T> clazz) {
    this.clazz = clazz;
  }

  public final void setClazz(final Class<T> clazzToSet) {
    clazz = clazzToSet;
  }

  @Override
  public T findOne(final long id) {
    return getCurrentSession().get(clazz, id);
  }

  @Override
  public List<T> findAll() {
    return (List<T>) getCurrentSession().createQuery("from " + clazz.getName()).list();
  }

  @Override
  public T create(final T entity) {
    requireNonNull(entity);
    getCurrentSession().saveOrUpdate(entity);
    return entity;
  }

  @Override
  public T update(final T entity) {
    requireNonNull(entity);
    return (T) getCurrentSession().merge(entity);
  }

  @Override
  public void delete(final T entity) {
    requireNonNull(entity);
    getCurrentSession().delete(entity);
  }

  @Override
  public void deleteById(final long entityId) {
    final T entity = findOne(entityId);
    requireNonNull(entity);
    delete(entity);
  }

  protected Session getCurrentSession() {
    return sessionFactory.getCurrentSession();
  }

  private void requireNonNull(T entity) {
    Objects.requireNonNull(entity, "Entity should not be null");
  }
}
