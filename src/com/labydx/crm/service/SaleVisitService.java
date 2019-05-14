package com.labydx.crm.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.labydx.crm.domain.PageBean;
import com.labydx.crm.domain.SaleVisit;

public interface SaleVisitService {

	void save(SaleVisit saleVisit);

	List<SaleVisit> findAll();

	PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	SaleVisit findById(Long visit_id);

	void delete(SaleVisit saleVisit);

	void update(SaleVisit saleVisit);
}
