package com.labydx.crm.dao.impl;

import java.util.List;

import com.labydx.crm.dao.BaseDictDao;
import com.labydx.crm.domain.BaseDict;

public class BaseDictDaoImpl extends BaseDaoImpl<BaseDict> implements BaseDictDao{
//
//	public BaseDictDaoImpl() {
//		super(BaseDict.class);
//	}

	@Override
	public List<BaseDict> findByTypeCode(String typecode) {
		return (List<BaseDict>) this.getHibernateTemplate().find("from BaseDict where dict_type_code=?", typecode);
	}

}
