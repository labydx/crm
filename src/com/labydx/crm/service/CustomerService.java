package com.labydx.crm.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.labydx.crm.domain.Customer;
import com.labydx.crm.domain.PageBean;

public interface CustomerService {

	void save(Customer customer);

	List<Customer> findAll();

	PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	Customer findById(Long cust_id);

	void delete(Customer customer);

	void update(Customer customer);

}
