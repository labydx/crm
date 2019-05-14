package com.labydx.crm.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.labydx.crm.domain.BaseDict;
import com.labydx.crm.service.BaseDictService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class BaseDictAction extends ActionSupport implements ModelDriven<BaseDict> {

	private BaseDictService baseDictService;
	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}
	
	private BaseDict baseDict=new BaseDict();
	@Override
	public BaseDict getModel() {
		return baseDict;
	}
	public String findByTypeCode() throws IOException{
			
		List<BaseDict> list=baseDictService.findByTypeCode(baseDict.getDict_type_code());
		System.out.println(list.size());
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"dict_item_code","dict_sort","dict_enable","dict_memo"});
		JSONArray jsonArray=JSONArray.fromObject(list,jsonConfig);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		System.out.println(jsonArray.toString());
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;
	}

}
