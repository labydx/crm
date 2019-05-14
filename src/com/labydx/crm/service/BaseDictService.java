package com.labydx.crm.service;

import java.util.List;

import com.labydx.crm.domain.BaseDict;

public interface BaseDictService {

	List<BaseDict> findByTypeCode(String string);

}
