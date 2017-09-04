package com.david.mavenessh.web.action.sysadmin;

import java.util.List;

import com.david.mavenessh.domain.Dept;
import com.david.mavenessh.domain.Role;
import com.david.mavenessh.domain.User;
import com.david.mavenessh.service.DeptService;
import com.david.mavenessh.service.RoleService;
import com.david.mavenessh.service.UserService;
import com.david.mavenessh.utils.Page;
import com.david.mavenessh.web.action.base.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 用户的action
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class UserAction extends BaseAction implements ModelDriven<User>{
	private User model = new User();
	@Override
	public User getModel() {
		return model;
	}

	private Page page = new Page();
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
	/**
	 * 注入用户的userService
	 */
	
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	private DeptService deptService;
	
	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	private RoleService roleService;
	
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	/**
	 * 分页查询
	 * @return
	 */
	public String list(){
		//SysConstant.PAGE_SIZE = 2;//显示两条每页数据 默认是十条
		userService.findPage("from User", page, User.class, null);
		
		//设置分页的url地址
		page.setUrl("userAction_list");
		
		//将page对象让压入栈顶
		super.push(page);
		return "list";
	}
	/**
	 * 进入新增页面
	 */
	public String tocreate(){
		//调用业务方法
		List<Dept> deptList = deptService.find("from Dept where state=1", Dept.class, null);
		super.put("deptList", deptList);
		
		List<User> userList = userService.find("from User where state=1", User.class, null);
		super.put("userList", userList);
		
		return "tocreate";
	}
	/**
	 * 进入角色的页面
	 */
	public String torole(){
		//调用业务方法
		User user = userService.get(User.class,model.getId());
		super.push(user);
		
		List<Role> roleList = roleService.find("from Role", Role.class, null);
		super.put("roleList", roleList);
		
		return "torole";
	}
	/**
	 * 保存
	 */
	public String insert(){
//		System.out.println(model.getDept().getId());
//		System.out.println(model);
		userService.saveOrUpdate(model);
		return "alist";
	}
	
	/**
	 * 查看
	 */
	public String toview(){
		//调用业务方法，得到对象
		User user = userService.get(User.class,model.getId());
		super.push(user);
		return "toview";
	}
	/**
	 * 进入修改页面
	 */
	public String toupdate(){
		User user = userService.get(User.class,model.getId());
		super.push(user);
		//查询父部门
		List<Dept> deptList = deptService.find("from Dept where state=1", Dept.class, null);
		//将查询结果放入到值栈中 。
		super.put("deptList", deptList);
		return "toupdate";
	}
	
	/**
	 * 修改
	 */
	public String update()throws Exception{
		/**
		 * 获取user对象
		 */
		User user = userService.get(User.class, model.getId());
		//设置要修改的属性  部门，状态，用户名
		user.setDept(model.getDept());
		user.setUserName(model.getUserName());
		user.setState(model.getState());
		userService.saveOrUpdate(user);
		return "alist";
	}
	
	/**
	 * 删除 有批量所以接过来的数据，会自动用逗号拼接。这里要截取
	 */
	public String delete(){
		String ids[] = model.getId().split(", ");
		//调用service实现批量删除
		userService.delete(User.class, ids);
		return "alist";
	}
}
