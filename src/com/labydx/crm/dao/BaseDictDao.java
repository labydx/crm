package com.labydx.crm.dao;

import java.util.List;

import com.labydx.crm.domain.BaseDict;

public interface BaseDictDao extends BaseDao<BaseDict>{

	List<BaseDict> findByTypeCode(String typecode);

}
