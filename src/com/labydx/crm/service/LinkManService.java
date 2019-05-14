package com.labydx.crm.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.labydx.crm.domain.LinkMan;
import com.labydx.crm.domain.PageBean;

public interface LinkManService {

	void save(LinkMan linkMan);
	List<LinkMan> findAll();

	PageBean<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	LinkMan findById(Long lkm_id);

	void delete(LinkMan linkMan);

	void update(LinkMan linkMan);
}
