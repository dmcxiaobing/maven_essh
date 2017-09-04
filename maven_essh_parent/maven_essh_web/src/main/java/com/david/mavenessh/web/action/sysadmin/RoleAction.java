package com.david.mavenessh.web.action.sysadmin;

import java.util.List;

import com.david.mavenessh.domain.Role;
import com.david.mavenessh.service.RoleService;
import com.david.mavenessh.utils.Page;
import com.david.mavenessh.web.action.base.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
/*
 * 角色管理action
 */
public class RoleAction extends BaseAction implements ModelDriven<Role> {

	private Role model = new Role();
	
	@Override
	public Role getModel() {
		return model;
	}
	//分页查询
	private Page page = new Page();

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	//注入roleService
	private RoleService roleService;

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	/**
	 * 分页查询 角色数据
	 */
	public String list(){
		roleService.findPage("from Role", page,Role.class, null);
		//设置分页的url地址
		page.setUrl("roleAction_list");
		//将page对象亚茹栈顶
		super.push(page);
		return "list";
	}
	/**
	 * 去查看--角色详情
	 */
	public String toview(){
		Role role = roleService.get(Role.class, model.getId());
		super.push(role);
		return "toview";
	}
	
	/**
	 * 去新增--界面
	 */
	public String tocreate(){
		return "tocreate";
	}
	
	
	/**
	 * 新增保存
	 */
	public String insert(){
		roleService.saveOrUpdate(model);
		return "alist";
	}
	/**
	 * 进入修改页面
	 */
	public String toupdate(){
		Role role = roleService.get(Role.class, model.getId());
		super.push(role);
		return "toupdate";
	}
	/**
	 * 修改--保存
	 */
	public String update(){
		//首先获取到要修改的角色
		Role  role = roleService.get(Role.class,model.getId());
		//设置最新的信息
		role.setName(model.getName());
		role.setRemark(model.getRemark());
		//调用更新的方法
		roleService.saveOrUpdate(role);
		return "alist";
	}
	/**
	 * 删除
	 */
	public String delete(){
		//首先 批量删除将id以逗号进行分割封装到数组中。
		String ids[] = model.getId().split(",");
		//调用业务的方法  实现批量删除
		roleService.delete(Role.class, ids);
		return "alist";
	}
	
	/**
	 * 权限
	 */
	public String tomodule(){
		return "tomodule";
	}
	
}
