package com.labydx.crm.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.labydx.crm.domain.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class PrivilegesInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		User exsitUser=(User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(exsitUser==null){
			ActionSupport actionSupport=(ActionSupport) invocation.getAction();
			actionSupport.addActionError("您还没有登录,请先登录");
			return actionSupport.LOGIN; 
		}else{
			return invocation.invoke();
		}
	}

}
