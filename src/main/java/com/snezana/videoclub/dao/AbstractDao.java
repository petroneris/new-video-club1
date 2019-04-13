package com.snezana.videoclub.dao;

import java.io.Serializable;

import java.lang.reflect.ParameterizedType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * DAO parent class with implemented CRUD session handling.
 * 
 * @param <PK> Session key object.
 * @param <T> (generic) Session which is being persisted.
 */
public abstract class AbstractDao<PK extends Serializable, T> {
	
	private final Class<T> persistentClass;
	
	@SuppressWarnings("unchecked")
	public AbstractDao(){
		this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	/**
	 * Returns session object found by session key.
	 * @param key Session key to search for.
	 */
	@SuppressWarnings("unchecked")
	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	/**
	 * Persists the session object.
	 * @param entity
	 */
	public void persist(T entity) {
		getSession().persist(entity);
	}

	/**
	 * Deletes the session object.
	 * @param entity
	 */
	public void delete(T entity) {
		getSession().delete(entity);
	}
	
	/** 
	 * Creates and returns Hibernate session Criteria object.
	 * @see org.Hibernate.Criteria
	 */
	protected Criteria createEntityCriteria(){
		return getSession().createCriteria(persistentClass);
	}

	
}
