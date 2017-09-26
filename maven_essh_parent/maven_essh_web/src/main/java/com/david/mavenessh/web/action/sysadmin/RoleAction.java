package com.david.mavenessh.web.action.sysadmin;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.david.mavenessh.domain.Module;
import com.david.mavenessh.domain.Role;
import com.david.mavenessh.service.ModuleService;
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

	// 分页查询
	private Page page = new Page();

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	// 注入roleService
	private RoleService roleService;

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	// 注入moduleService
	private ModuleService moduleService;
	
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	/**
	 * 分页查询 角色数据
	 */
	public String list() {
		roleService.findPage("from Role", page, Role.class, null);
		// 设置分页的url地址
		page.setUrl("roleAction_list");
		// 将page对象亚茹栈顶
		super.push(page);
		return "list";
	}

	/**
	 * 去查看--角色详情
	 */
	public String toview() {
		Role role = roleService.get(Role.class, model.getId());
		super.push(role);
		return "toview";
	}

	/**
	 * 去新增--界面
	 */
	public String tocreate() {
		return "tocreate";
	}

	/**
	 * 新增保存
	 */
	public String insert() {
		roleService.saveOrUpdate(model);
		return "alist";
	}

	/**
	 * 进入修改页面
	 */
	public String toupdate() {
		Role role = roleService.get(Role.class, model.getId());
		super.push(role);
		return "toupdate";
	}

	/**
	 * 修改--保存
	 */
	public String update() {
		// 首先获取到要修改的角色
		Role role = roleService.get(Role.class, model.getId());
		// 设置最新的信息
		role.setName(model.getName());
		role.setRemark(model.getRemark());
		// 调用更新的方法
		roleService.saveOrUpdate(role);
		return "alist";
	}

	/**
	 * 删除
	 */
	public String delete() {
		// 首先 批量删除将id以逗号进行分割封装到数组中。
		String ids[] = model.getId().split(",");
		// 调用业务的方法 实现批量删除
		roleService.delete(Role.class, ids);
		return "alist";
	}

	/**
	 * 进入权限模块的页面
	 */
	public String tomodule() {
		// 根据角色ID，得到权限模块
		Role obj = roleService.get(Role.class, model.getId());
		super.push(obj);
		return "tomodule";
	}

	/**
	 * 使用zTree树，组织好zTree树使用的所有json数据 json数据结构如下：
	 * [{"id":"模块的id","pId":"父模块id","name":"模块名","checked":"true|false"},
	 * {"id": "模块的id","pId":"父模块id","name":"模块名","checked":"true|false"}]
	 * 
	 * 常用的json插件有哪些？ json-lib fastjson struts-json-plugin-xxx.jar,手动拼接
	 * 
	 * 如何输出? 借助于response对象输出数据
	 */
	public String roleModuleJsonStr() throws Exception {
		//根据角色Id,得到角色对象
		Role role = roleService.get(Role.class, model.getId());
		//通过对象方式，加载出当前角色的模块列表
		Set<Module> moduleSet = role.getModules();
		//加载出所有的模块
		List<Module> moduleList = moduleService.find("from Module", Module.class,null);
		int size = moduleList.size();//得到所有模块的数量
		StringBuffer sb = new StringBuffer();
		//组织json字符串
		sb.append("[");
		for (Module module : moduleList) {
			size--;
			sb.append("{\"id\":\"").append(module.getId());
			sb.append("\",\"pId\":\"").append(module.getParentId());
			sb.append("\",\"name\":\"").append(module.getName());
			sb.append("\",\"checked\":\"");
			if(moduleSet.contains(module)){
				sb.append("true");
			}else{
				sb.append("false");
			}
			sb.append("\"}");
			if(size>0){
				sb.append(",");
			}
		}
		
		sb.append("]");
		///5.得到response对象
		HttpServletResponse response = ServletActionContext.getResponse();
		
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		//6.使用 response对象输出json串
		response.getWriter().write(sb.toString());
		//7.返回NONE
		return NONE;
	}
	
	private String moduleIds;
	
	
	public void setModuleIds(String moduleIds) {
		this.moduleIds = moduleIds;
	}

	/**
	 * 保存当前角色的模块列表
	 * 	<input type="hidden" name="id" value="${id}"/>
	    <input type="hidden" id="moduleIds" name="moduleIds" value="" />
	 * 
	 */
	public String module() throws Exception {
		//首先获取到要更改的角色ID
		Role role = roleService.get(Role.class,model.getId());
		//得到选中的模块
		String[] ids = moduleIds.split(",");
		/**
		 * 然后得到这些模块的名称
		 */
		Set<Module> moduleSet  = new HashSet<Module>();
		if (ids!=null && ids.length>0) {
			for (String id : ids) {
				moduleSet.add(moduleService.get(Module.class, id));//添加选中的模块，放入到模块列表中
			}
		}
		//实现角色分配新的模块
		role.setModules(moduleSet);
		//更新数据库
		roleService.saveOrUpdate(role);
		return "alist";
	}
}
