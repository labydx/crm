package com.labydx.crm.service.impl;

import java.util.List;

import com.labydx.crm.dao.BaseDictDao;
import com.labydx.crm.domain.BaseDict;
import com.labydx.crm.service.BaseDictService;

public class BaseDictServiceImpl implements BaseDictService {

	private BaseDictDao baseDictDao;
	public void setBaseDictDao(BaseDictDao baseDictDao) {
		this.baseDictDao = baseDictDao;
	}

	@Override
	public List<BaseDict> findByTypeCode(String typecode) {
		return baseDictDao.findByTypeCode(typecode);
	}

}
