package com.labydx.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.labydx.crm.dao.CustomerDao;
import com.labydx.crm.domain.Customer;
import com.labydx.crm.domain.PageBean;
import com.labydx.crm.service.CustomerService;

@Transactional
public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	@Override
	public void save(Customer customer) {
		customerDao.save(customer);
		
	}
	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}
	@Override
	public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		//最后需要返回pageBean参数
		PageBean<Customer> pageBean=new PageBean<Customer>();
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		//通过总记录数,得到总页数的过程
		Integer rowcounts=customerDao.findCount(detachedCriteria);
		pageBean.setTotalCount(rowcounts);
		Double tc=rowcounts.doubleValue();
		Double num=Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		//通过当前记录,取出的记录数,得到要在页面上显示的list集合,并封装在pageBean中
		Integer begin=(currPage-1)*pageSize;
		List<Customer> list=customerDao.findByPage(detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}
	@Override
	public Customer findById(Long cust_id) {
		return customerDao.findById(cust_id);
	}
	@Override
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}
	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
	}
	
	
}
