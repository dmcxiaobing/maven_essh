package com.david.mavenessh.web.action.sysadmin;

import java.util.List;

import com.david.mavenessh.domain.Dept;
import com.david.mavenessh.domain.Module;
import com.david.mavenessh.domain.User;
import com.david.mavenessh.service.DeptService;
import com.david.mavenessh.service.ModuleService;
import com.david.mavenessh.service.UserService;
import com.david.mavenessh.utils.Page;
import com.david.mavenessh.web.action.base.BaseAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 模块管理action
 * 
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class ModuleAction extends BaseAction implements ModelDriven<Module> {
	private Module module = new Module();

	@Override
	public Module getModel() {
		return module;
	}
	//分页查询
	private Page page = new Page();
	
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	//注入moduleService
	private ModuleService moduleService;

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}
	
	private DeptService deptService;
	
	
	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	private UserService userService;
	
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 分页查看列表
	 */
	public String list(){
		moduleService.findPage("from Module", page, Module.class, null);
		//设置分页的URL地址
		page.setUrl("moduleAction_list");
		//将page对象压入栈顶
		super.push(page);
		return "list";
	}
	
	/**
	 * 进入查看界面
	 */
	public String toview(){
		Module obj = moduleService.get(Module.class,module.getId());
		//将对象放入值栈中
		super.push(obj);
		return "toview";
	}
	
	/**
	 * 进入新增列表
	 */
	public String tocreate(){
		return "tocreate";
	}
	/**
	 * 新增---保存
	 */
	public String insert(){
		//调用业务方法，实现保存
		moduleService.saveOrUpdate(module);
		return "alist";
	}
	/**
	 * 进入修改页面
	 */
	public String toupdate(){
		//根据id，得到一个对象
		Module obj = moduleService.get(Module.class,module.getId());
		//将对象放入值栈中
		super.push(obj);
		return "toupdate";
	}
	/**
	 * 修改--保存
	 */
	public String update(){
		//调用业务方法， 根据ID获取对象
		Module obj = moduleService.get(Module.class,module.getId());
		//设置修改的属性
		obj.setName(module.getName());
		obj.setLayerNum(module.getLayerNum());
		obj.setCpermission(module.getCpermission());
		obj.setCurl(module.getCurl());
		obj.setCtype(module.getCtype());
		obj.setState(module.getState());
		obj.setBelong(module.getBelong());
		obj.setCwhich(module.getCwhich());
		obj.setRemark(module.getRemark());
		obj.setOrderNo(module.getOrderNo());
		
		moduleService.saveOrUpdate(obj);
		return "alist";
	}
	/**
	 * 删除
	 */
	public String delete() throws Exception {
		String ids[] = module.getId().split(", ");
		
		//调用业务方法，实现批量删除
		moduleService.delete(Module.class, ids);
		
		return "alist";
	}
}
