package com.david.mavenessh.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 角色的Javabean  
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class Role extends BaseEntity{
	private String id;
	private String name;//角色名
	private String remark;//备注名
	private String orderNo;//排序号
	private Set<User> users = new HashSet<User>(0);//角色与用户  多对多
	private Set<Module> modules = new HashSet<Module>(0);//角色与模块，多对多
	
	public Set<Module> getModules() {
		return modules;
	}
	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
}

