package com.labydx.crm.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.labydx.crm.domain.BaseDict;
import com.labydx.crm.domain.Customer;
import com.labydx.crm.domain.PageBean;
import com.labydx.crm.service.CustomerService;
import com.labydx.crm.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

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
	//文件上传属性获取
	private String uploadFileName;
	private String uploadContentType;
	private File upload;
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	private Customer customer=new Customer();
	@Override
	public Customer getModel() {
		return customer;
	}
	
	public String findAll(){
		if(pageSize==null) pageSize=3;
		if(currPage==null) currPage=1;
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Customer.class);
				/*.setFetchMode("this.baseDictIndustry", FetchMode.DEFAULT).
				setFetchMode("this.baseDictSource", FetchMode.DEFAULT).
				setFetchMode("this.baseDictLevel", FetchMode.DEFAULT);*/
		if(customer.getCust_name()!=null && !"".equals(customer.getCust_name())){
			detachedCriteria.add(Restrictions.like("cust_name", "%"+customer.getCust_name()+"%"));
		}
		if(customer.getBaseDictSource()!=null)
			if(customer.getBaseDictSource().getDict_id()!=null && !"".equals(customer.getBaseDictSource().getDict_id())){
				detachedCriteria.add(Restrictions.eq("baseDictSource.dict_id", customer.getBaseDictSource().getDict_id()));
		}
		if(customer.getBaseDictLevel()!=null)
			if(customer.getBaseDictLevel().getDict_id()!=null && !"".equals(customer.getBaseDictLevel().getDict_id())){
				detachedCriteria.add(Restrictions.eq("baseDictLevel.dict_id", customer.getBaseDictLevel().getDict_id()));
		}
		if(customer.getBaseDictIndustry()!=null)
			if(customer.getBaseDictIndustry().getDict_id()!=null && !"".equals(customer.getBaseDictIndustry().getDict_id())){
				detachedCriteria.add(Restrictions.eq("baseDictIndustry.dict_id", customer.getBaseDictIndustry().getDict_id()));
		}
		PageBean<Customer> pageBean=new PageBean();
		pageBean=customerService.findByPage(detachedCriteria,currPage,pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	public String save() throws IOException{
		if(upload != null){
			String path="D:/upload";
			String uuidFileName=UploadUtils.UuidFileName(uploadFileName);
			//创建不许目录
			String realPath=UploadUtils.getPath(uuidFileName);
			String url=path+realPath;
			File file=new File(url);
			if(!file.exists())
				file.mkdirs();
			//创建要被存放的文件:目录+文件名
			File dictFile=new File(url+"/"+uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			customer.setCust_image(url+"/"+uuidFileName);
		}
		customerService.save(customer);
		return "redirectToList";
	}
	public String update() throws IOException{
		if(upload != null){
			String cust_image=customer.getCust_image();
			if(cust_image!=null||"".equals(cust_image)){
				File file=new File(cust_image);
				if(file.exists()){
					file.delete();
				}
			}
			String path="D:/upload";
			String uuidFileName=UploadUtils.UuidFileName(uploadFileName);
			//创建不许目录
			String realPath=UploadUtils.getPath(uuidFileName);
			String url=path+realPath;
			File file=new File(url);
			if(!file.exists())
				file.mkdirs();
			//创建要被存放的文件:目录+文件名
			File dictFile=new File(url+"/"+uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			customer.setCust_image(url+"/"+uuidFileName);
		}
		customerService.update(customer);
		return "redirectToList";
	}
	public String editUI(){
		//用模型驱动的方式放入数据,模型自动被放在栈顶
		customer=customerService.findById(customer.getCust_id());
		return "editUI";
	}
	public String delete(){
		customer=customerService.findById(customer.getCust_id());
		if(customer.getCust_image() != null){
			File file=new File(customer.getCust_image());
			if(file.exists()){
				file.delete();
			}
		}
		customerService.delete(customer);
		return "redirectToList";
	}
	//用于异步加载customer数据
	public String findAllCustomer() throws IOException{
		List<Customer> list=customerService.findAll();
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"cust_phone","cust_mobile","cust_image","baseDictSource","baseDictIndustry","baseDictLevel","linkMans"});
		JSONArray jsonArray=JSONArray.fromObject(list,jsonConfig);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;
	}
}
