package com.david.mavenessh.web.action.common;

import com.david.mavenessh.web.action.base.BaseAction;

public class HomeAction extends BaseAction{
	private String moduleName;		//动态指定跳转的模块，在struts.xml中配置动态的result
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String fmain(){
		return "fmain";
	}
	
	public String title(){
		//获取session
		//User curUser = (User)session.get(SysConstant.CURRENT_USER_INFO);
		//ActionContext.getContext().getValueStack().push(curUser);
		
		return "title";
	}

	//转向moduleName指向的模块
	public String tomain(){
		//获取request

		return "tomain";
	}
	public String toleft(){
		//获取request
		
		return "toleft";
	}

}
