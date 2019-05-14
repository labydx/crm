package com.labydx.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.labydx.crm.dao.SaleVisitDao;
import com.labydx.crm.domain.Customer;
import com.labydx.crm.domain.PageBean;
import com.labydx.crm.domain.SaleVisit;
import com.labydx.crm.service.SaleVisitService;

@Transactional
public class SaleVisitServiceImpl implements SaleVisitService{

	@Resource(name="saleVisitDao")
	private SaleVisitDao saleVisitDao;
	@Override
	public void save(SaleVisit saleVisit) {
		saleVisitDao.save(saleVisit);
	}

	@Override
	public List<SaleVisit> findAll() {
		return saleVisitDao.findAll();
	}

	@Override
	public PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		//最后需要返回pageBean参数
		PageBean<SaleVisit> pageBean=new PageBean<SaleVisit>();
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		//通过总记录数,得到总页数的过程
		Integer rowcounts=saleVisitDao.findCount(detachedCriteria);
		pageBean.setTotalCount(rowcounts);
		Double tc=rowcounts.doubleValue();
		Double num=Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		//通过当前记录,取出的记录数,得到要在页面上显示的list集合,并封装在pageBean中
		Integer begin=(currPage-1)*pageSize;
		List<SaleVisit> list=saleVisitDao.findByPage(detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public SaleVisit findById(Long visit_id) {
		return saleVisitDao.findById(visit_id);
	}

	@Override
	public void delete(SaleVisit saleVisit) {
		saleVisitDao.delete(saleVisit);
	}

	@Override
	public void update(SaleVisit saleVisit) {
		saleVisitDao.update(saleVisit);
	}

}
