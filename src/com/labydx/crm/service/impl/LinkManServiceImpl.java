package com.labydx.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.labydx.crm.dao.LinkManDao;
import com.labydx.crm.domain.LinkMan;
import com.labydx.crm.domain.PageBean;
import com.labydx.crm.service.LinkManService;

@Transactional
public class LinkManServiceImpl implements LinkManService {

	private LinkManDao linkManDao;
	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}
	
	@Override
	public void save(LinkMan linkMan) {
		linkManDao.save(linkMan);
	}

	@Override
	public List<LinkMan> findAll() {
		return linkManDao.findAll();
	}

	@Override
	public PageBean<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean pageBean=new PageBean();
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		
		Integer totalCount=linkManDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		
		Double rowCounts=totalCount.doubleValue();
		Double num=Math.ceil(totalCount/pageSize);
		pageBean.setTotalPage(num.intValue());
		
		int begin=(currPage-1)*pageSize;
		List<LinkMan> list=linkManDao.findByPage(detachedCriteria, begin, pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public LinkMan findById(Long lkm_id) {
		return linkManDao.findById(lkm_id);
	}

	@Override
	public void delete(LinkMan linkMan) {
		linkManDao.delete(linkMan);
	}

	@Override
	public void update(LinkMan linkMan) {
		linkManDao.update(linkMan);
	}
}
