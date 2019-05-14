package com.labydx.crm.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface BaseDao<T> {

	void save(T t);

	List<T> findAll();

	Integer findCount(DetachedCriteria detachedCriteria);

	List<T> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);

	T findById(Serializable id);

	void delete(T t);

	void update(T t);
}
