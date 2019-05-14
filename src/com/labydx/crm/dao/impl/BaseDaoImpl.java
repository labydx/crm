package com.labydx.crm.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.labydx.crm.dao.BaseDao;

public class BaseDaoImpl<T> extends HibernateDaoSupport  implements BaseDao<T> {

	private Class clazz;
	/*public BaseDaoImpl(Class clazz) {
		this.clazz = clazz;
	}*/

	public BaseDaoImpl() {
		Class clazz=this.getClass();
		Type type=clazz.getGenericSuperclass();
		ParameterizedType  ptype=(ParameterizedType)type;
		Type[] types=ptype.getActualTypeArguments();
		this.clazz=(Class) types[0];
	}
	
	@Override
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	@Override
	public List findAll() {
		return this.getHibernateTemplate().find("from "+clazz.getSimpleName());
	}

	@Override
	public Integer findCount(DetachedCriteria detachedCriteria) {
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list=(List<Long>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if(list.size()>0)
			return list.get(0).intValue();
		return null;
	}

	@Override
	public List<T> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
		detachedCriteria.setProjection(null);
		return (List<T>)this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, pageSize);
	}

	@Override
	public T findById(Serializable id) {
		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}
}
