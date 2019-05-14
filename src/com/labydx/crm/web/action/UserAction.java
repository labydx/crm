package com.labydx.crm.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.labydx.crm.domain.User;
import com.labydx.crm.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
/**
 * 用户管理的Action的类
 * @author jt
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {
	// 模型驱动使用的对象
	private User user = new User();
	@Override
	public User getModel() {
		return user;
	}

	// 注入Service:
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 用户注册的方法:regist
	 */
	public String regist(){
		userService.regist(user);
		return LOGIN;
	}
	public String login(){
		User existUser=userService.login(user);
		if(existUser==null){
			this.addActionError("用户名或密码错误");
			return LOGIN;
		}else{
			ActionContext.getContext().getSession().put("existUser", existUser);
			return SUCCESS;
		}
	}
	
	public String findAllUser() throws IOException{
		List<User> list=userService.findAll();
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"password"});
		JSONArray jsonArray=JSONArray.fromObject(list,jsonConfig);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;
	}
}
