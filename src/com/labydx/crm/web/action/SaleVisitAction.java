package com.labydx.crm.web.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.labydx.crm.domain.PageBean;
import com.labydx.crm.domain.SaleVisit;
import com.labydx.crm.service.SaleVisitService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {

	private SaleVisit saleVisit=new SaleVisit();
	@Override
	public SaleVisit getModel() {
		return saleVisit;
	}
	
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
	@Resource(name="saleVisitService")
	private SaleVisitService saleVisitService;
	//按照时间查询并实现回显
	private Date visit_end_time;
	public Date getVisit_end_time() {
		return visit_end_time;
	}
	public void setVisit_end_time(Date visit_end_time) {
		this.visit_end_time = visit_end_time;
	}
	public String findAll(){
		if(pageSize==null) pageSize=3;
		if(currPage==null) currPage=1;
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(SaleVisit.class);
		if(saleVisit.getVisit_time()!=null && "".equals(saleVisit.getVisit_time())){
			detachedCriteria.add(Restrictions.gt("visit_time", saleVisit.getVisit_time()));
		}
		if(visit_end_time!=null && "".equals(visit_end_time)){
			detachedCriteria.add(Restrictions.lt("visit_time", visit_end_time));
		}
		if(saleVisit.getCustomer()!=null)
			if(saleVisit.getCustomer().getCust_id()!=null && !"".equals(saleVisit.getCustomer().getCust_id())){
				detachedCriteria.add(Restrictions.eq("customer.cust_id", saleVisit.getCustomer().getCust_id()));
		}
		if(saleVisit.getUser()!=null)
			if(saleVisit.getUser().getUser_id() !=null && !"".equals(saleVisit.getUser().getUser_id())){
				detachedCriteria.add(Restrictions.eq("user.user_id", saleVisit.getUser().getUser_id()));
		}
		
		PageBean pageBean=saleVisitService.findByPage(detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	public String editUI(){
		saleVisit=saleVisitService.findById(saleVisit.getVisit_id());
		ActionContext.getContext().getValueStack().push(saleVisit);
		return "editUI";
	}
	public String saveUI(){
		return "saveUI";
	}
	public String delete(){
		saleVisitService.delete(saleVisit);
		return "redirectToList";
	}
	public String update(){
		saleVisitService.update(saleVisit);
		return "redirectToList";
	}
	public String save(){
		saleVisitService.save(saleVisit);
		return "redirectToList";
	}
	
}
