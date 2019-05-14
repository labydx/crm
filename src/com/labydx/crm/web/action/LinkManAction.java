package com.labydx.crm.web.action;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.labydx.crm.domain.Customer;
import com.labydx.crm.domain.LinkMan;
import com.labydx.crm.domain.PageBean;
import com.labydx.crm.service.CustomerService;
import com.labydx.crm.service.LinkManService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {

	//分页属性获取
	private Integer currPage;
	public void setCurrPage(Integer currPage) {
		if(currPage==null)
			currPage=1;
		this.currPage = currPage;
	}
	private Integer pageSize;
	public void setPageSize(Integer pageSize) {
		if(pageSize==null)
			pageSize=3;
		this.pageSize = pageSize;
	}
	
	private LinkMan linkMan=new LinkMan();
	@Override
	public LinkMan getModel() {
		return linkMan;
	}
	private LinkManService linkManService;
	
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	public String findAll(){
		if(pageSize==null) pageSize=3;
		if(currPage==null) currPage=1;
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(LinkMan.class);
		if(linkMan.getLkm_name()!=null && !"".equals(linkMan.getLkm_name())){
			detachedCriteria.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name()+"%"));
		}
		if(linkMan.getLkm_gender()!=null && !"".equals(linkMan.getLkm_gender())){
			detachedCriteria.add(Restrictions.eq("lkm_gender", linkMan.getLkm_gender()));
		}
		if(linkMan.getCustomer()!=null)
			if(linkMan.getCustomer().getCust_id() != null && !"".equals(linkMan.getCustomer().getCust_id())){
				detachedCriteria.add(Restrictions.eq("customer.cust_id", linkMan.getCustomer().getCust_id()));
		}
		PageBean pageBean=linkManService.findByPage(detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		List<Customer> list=customerService.findAll();
		ActionContext.getContext().getValueStack().set("customerlist", list);
		return "findAll";
	}
	public String editUI(){
		List<Customer> list=customerService.findAll();
		ActionContext.getContext().getValueStack().set("customerlist", list);
		linkMan=linkManService.findById(linkMan.getLkm_id());
		ActionContext.getContext().getValueStack().push(linkMan);
		return "editUI";
	}
	public String update(){
		linkManService.update(linkMan);
		return "redirectToList";
	}
	public String saveUI(){
		List<Customer> list=customerService.findAll();
		ActionContext.getContext().getValueStack().set("customerlist", list);
		
		return "saveUI";
	}
	public String save(){
		linkManService.save(linkMan);
		return "redirectToList";
	}
	public String delete(){
		linkMan=linkManService.findById(linkMan.getLkm_id());
		linkManService.delete(linkMan);
		return "redirectToList";
	}
}
